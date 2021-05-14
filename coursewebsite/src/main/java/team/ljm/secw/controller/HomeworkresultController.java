package team.ljm.secw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.dto.HomeworkResultDTO;
import team.ljm.secw.dto.ResourceDTO;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.service.IHomeworkresultService;
import team.ljm.secw.utils.FileUtil;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
public class HomeworkresultController {

    @Autowired
    private IHomeworkresultService homeworkResultService;

    //根据作业id获取结果列表
    @RequiresRoles("teacher")
    @RequestMapping("/teacher/homework_result/all")
    @ResponseBody
    public ResponseVO selectByHwId(@RequestParam("homeworkId")  int homeworkId,
                                   @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<HomeworkResult> list = homeworkResultService.findListByHwid(homeworkId);
        PageInfo<HomeworkResult> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200", "success", pageInfo);
    }

    //根据作业id获取提交情况
    @RequiresRoles("teacher")
    @RequestMapping("/teacher/homework_result/all_sub")
    @ResponseBody
    public ResponseVO selectInfoByHwId(@RequestParam("homeworkId")  int homeworkId,
                                   @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<HomeworkResultDTO> list = homeworkResultService.findInfoListById(homeworkId);
        PageInfo<HomeworkResultDTO> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200", "success", pageInfo);
    }

    //根据作业id获取未提交列表
    @RequiresRoles("teacher")
    @RequestMapping("/teacher/homework_result/not_sub")
    @ResponseBody
    public ResponseVO selectNoSub(@RequestParam("homeworkId")  int homeworkId,
                                   @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<HomeworkResultDTO> list = homeworkResultService.findNotSub(homeworkId);
        PageInfo<HomeworkResultDTO> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200", "success", pageInfo);
    }

    //根据作业id获取未批改列表
    @RequiresRoles("teacher")
    @RequestMapping("/teacher/homework_result/not_cor")
    @ResponseBody
    public ResponseVO selectNoCor(@RequestParam("homeworkId")  int homeworkId,
                                  @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<HomeworkResultDTO> list = homeworkResultService.findNotCor(homeworkId);
        PageInfo<HomeworkResultDTO> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200", "success", pageInfo);
    }

    //根据作业id获取已经批改列表
    @RequiresRoles("teacher")
    @RequestMapping("/teacher/homework_result/cor")
    @ResponseBody
    public ResponseVO selectCor(@RequestParam("homeworkId")  int homeworkId,
                                  @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<HomeworkResultDTO> list = homeworkResultService.findCor(homeworkId);
        PageInfo<HomeworkResultDTO> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200", "success", pageInfo);
    }

    //批改作业
    @RequiresRoles("teacher")
    @RequestMapping("/teacher/homework_result/update")
    @ResponseBody
    public ResponseVO update(@RequestBody HomeworkResult requestHomeworkResult){
        int id = requestHomeworkResult.getId();
        homeworkResultService.correct(requestHomeworkResult);
        return new ResponseVO("200","success");
    }

    //删除一个作业结果
    @RequiresRoles("teacher")
    @RequestMapping("/teacher/homework_result/delete")
    @ResponseBody
    public ResponseVO delete(@RequestBody HomeworkResult requestHomeworkResult, HttpServletRequest request){
        String url = request.getSession().getServletContext().getRealPath(requestHomeworkResult.getFilePath());
        Path path = Paths.get(url);
        try {
            //删除原附件
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        homeworkResultService.remove(requestHomeworkResult);
        return new ResponseVO("200","success");
    }

    //学生提交作业（包括更新）
    //@RequiresRoles("student")
    @RequestMapping("/student/homework_result/submit")
    @ResponseBody
    public ResponseVO submit(@ModelAttribute HomeworkResultDTO requestHomeworkResult, HttpServletRequest request, Model model) {
        MultipartFile file = requestHomeworkResult.getFile();
        if (file != null) {
            //将附件储存
            try {
                String originalFileName = file.getOriginalFilename();
                String fileUrl = "";
                fileUrl = "/WEB-INF/homework/" + requestHomeworkResult.getHomeworkId() + "/" + requestHomeworkResult.getStudentId() + "/" + originalFileName;
                fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
                Date date = new Date();
                requestHomeworkResult.setSubmittedAt(date);
                //向url地址存储文件
                FileUtil.writeFileToUrl(file, fileUrl);
                requestHomeworkResult.setFilePath(fileUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HomeworkResult homeworkResult = new HomeworkResult();
        homeworkResult.setHomeworkId(requestHomeworkResult.getHomeworkId());
        homeworkResult.setStudentId(requestHomeworkResult.getStudentId());

        if (requestHomeworkResult.getFilePath() != null) {
            homeworkResult.setFilePath(requestHomeworkResult.getFilePath());
        } else {
            homeworkResult.setFilePath("");
        }

        homeworkResult.setContent(requestHomeworkResult.getContent());
        Integer id = homeworkResultService.findResultStatus(homeworkResult);
        if (id == null){
            HomeworkResult homeworkResult1 = new HomeworkResult();
            homeworkResult1.setHomeworkId(requestHomeworkResult.getHomeworkId());
            homeworkResult1.setStudentId(requestHomeworkResult.getStudentId());
            homeworkResultService.add(homeworkResult1);
        }
        Date date = new Date();
        requestHomeworkResult.setSubmittedAt(date);
        //已经交过，判定为更新提交结果
        if (id == -1){
            HomeworkResult homeworkResult1 = homeworkResultService.findResultStu(homeworkResult);
            String url = homeworkResult1.getFilePath();
            Path path = Paths.get(url);
            try {
                //删除原附件
                Files.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        homeworkResult.setScore(-1);
        //更新结果
        homeworkResultService.modify(homeworkResult);
        return new ResponseVO("200","success");
    }

    //学生查看已提交详情
    @RequiresRoles("student")
    @RequestMapping("/student/homework_result/search")
    @ResponseBody
    public ResponseVO selectByHwIdStuId(@RequestParam("studentId") int studentId,
                                        @RequestParam("homeworkId") int homework){
        HomeworkResult homeworkResult = new HomeworkResult();
        homeworkResult.setStudentId(studentId);
        homeworkResult.setHomeworkId(homework);
        homeworkResult = homeworkResultService.findResultStu(homeworkResult);
        return new ResponseVO("200","success",homeworkResult);
    }

    //下载附件
    //@RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/homework_result/download")
    public void download(HttpServletRequest request, HttpServletResponse response , @RequestParam("id") int id){
        try {
            HomeworkResult homeworkResult = homeworkResultService.findById(id);
            String filePath = homeworkResult.getFilePath().trim();
            String filename = filePath.substring(filePath.lastIndexOf("\\")+1);
            File file = new File(filePath);//如果文件存在的话
            if (file.exists()) {//获取输入流
                InputStream bis = new BufferedInputStream(new FileInputStream(file));//假如以中文名下载的话
                filename = URLEncoder.encode(filename, "UTF-8" );//设置文件下载头
                response.addHeader("Content-Disposition", "attachment;filename=" + filename);
                response.setContentType ( "multipart/form-data" );
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());int len = 0;
                while ((len = bis.read()) != -1) {
                    out.write(len);
                }
                out.close();
            }else
            {
                //return new ResponseVO("404","not found");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            //return new ResponseVO("500","error");
        }
        //return new ResponseVO("200","success");
    }

    //学生姓名查看
    @RequiresRoles("student")
    @RequestMapping("/student/homework_result/stu_info")
    @ResponseBody
    public ResponseVO selectStuById(@RequestParam("studentId") int studentId){
        Student student = homeworkResultService.findStuById(studentId);
        return new ResponseVO("200","success",student);
    }

    //学生查看提交列表
    @RequiresRoles("student")
    @RequestMapping("/student/homework_result/all")
    @ResponseBody
    public ResponseVO selectByStuId(@RequestParam("studentId")  int studentId,
                                    @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<HomeworkResultDTO> list = homeworkResultService.findListByStuId(studentId);
        PageInfo<HomeworkResultDTO> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200", "success", pageInfo);
    }

}
