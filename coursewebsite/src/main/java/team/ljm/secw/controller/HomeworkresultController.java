package team.ljm.secw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    @RequestMapping("/teacher/homework_result/all")
    @ResponseBody
    public ResponseVO selectByHwId(@RequestParam("id")  int id,
                                   @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<HomeworkResult> list = homeworkResultService.findListByHwid(id);
        PageInfo<HomeworkResult> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200", "success", pageInfo);
    }

    //批改作业
    @RequestMapping("/teacher/homework_result/update")
    @ResponseBody
    public ResponseVO update(@RequestBody HomeworkResult requestHomeworkResult){
        int id = requestHomeworkResult.getId();
        homeworkResultService.correct(requestHomeworkResult);
        return new ResponseVO("200","success");
    }

    //删除一个作业结果
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
    @RequestMapping("/student/homework_result/submit")
    @ResponseBody
    public ResponseVO submit(@RequestBody HomeworkResult requestHomeworkResult, @RequestBody MultipartFile file, HttpServletRequest request) {

        //将附件储存
        try {
            byte[] buf = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileUrl = "";
            Date date = new Date();
            requestHomeworkResult.setSubmittedAt(date);
            fileUrl = "/WEB-INF/homework/" + requestHomeworkResult.getHomeworkId() + "/" + requestHomeworkResult.getStudentId() + "/" + originalFileName;
            fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
            //向url地址存储文件
            FileUtil.writeFileToUrl(file, fileUrl);
            requestHomeworkResult.setFilePath(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = homeworkResultService.findResultStatus(requestHomeworkResult);
        Date date = new Date();
        requestHomeworkResult.setSubmittedAt(date);
        //已经交过，判定为更新提交结果
        if (id != -2){
            HomeworkResult homeworkResult = homeworkResultService.findById(id);
            String url = request.getSession().getServletContext().getRealPath(homeworkResult.getFilePath());
            Path path = Paths.get(url);
            try {
                //删除原附件
                Files.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //更新结果
        homeworkResultService.modify(requestHomeworkResult);
        return new ResponseVO("200","success");
    }

    //学生查看已提交详情
    @RequestMapping("/student/homework_result/search")
    @ResponseBody
    public ResponseVO selectByHwIdStuId(@RequestBody HomeworkResult requestHomeworkResult){
        HomeworkResult homeworkResult = homeworkResultService.findResultStu(requestHomeworkResult);
        return new ResponseVO("200","success",homeworkResult);
    }

    //下载附件
    @RequestMapping(value = "/homework_result/download")
    public void download(HttpServletRequest request, HttpServletResponse response , @RequestBody HomeworkResult requestHomeworkResult){
        try {
            String filePath =  request.getSession().getServletContext().getRealPath(requestHomeworkResult.getFilePath()).trim();
            String filename = filePath.substring(filePath.lastIndexOf("/")+1);
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

    //学生查看已提交详情
    @RequestMapping("/student/homework_result/stu_info")
    @ResponseBody
    public ResponseVO selectStuById(@RequestParam("id") int id){
        Student student = homeworkResultService.findStuById(id);
        return new ResponseVO("200","success",student);
    }

}
