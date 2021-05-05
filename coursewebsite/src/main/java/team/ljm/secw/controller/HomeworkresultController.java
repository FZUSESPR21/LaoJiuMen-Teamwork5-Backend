package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.dto.HomeworkresultDTO;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.entity.Resource;
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
@RequestMapping("/homework_result")
public class HomeworkresultController {

    @Autowired
    private IHomeworkresultService homeworkresultService;

    //根据作业id获取结果列表
    @RequestMapping("/teacher/list_search")
    @ResponseBody
    public ResponseVO selectByHwId(@RequestBody Homework requestHomework){
        int id = requestHomework.getId();
        List<HomeworkResult> list = homeworkresultService.findListByHwid(id);
        return new ResponseVO("200","success",list);
    }

    //批改作业
    @RequestMapping("/teacher/update")
    @ResponseBody
    public ResponseVO update(@RequestBody HomeworkresultDTO requestHomeworkresultDTO){
        int id = requestHomeworkresultDTO.getId();
        homeworkresultService.modify(requestHomeworkresultDTO);
        return new ResponseVO("200","success");
    }

    //删除一个作业结果
    @RequestMapping("/teacher/delete")
    @ResponseBody
    public ResponseVO delete(@RequestBody HomeworkresultDTO requestHomeworkresultDTO, HttpServletRequest request){
        String url = request.getSession().getServletContext().getRealPath(requestHomeworkresultDTO.getFilePath());
        Path path = Paths.get(url);
        try {
            //删除原附件
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        homeworkresultService.remove(requestHomeworkresultDTO);
        return new ResponseVO("200","success");
    }

    //学生提交作业（包括更新）
    @RequestMapping("/student/submit")
    @ResponseBody
    public ResponseVO submit(@RequestBody HomeworkresultDTO requestHomeworkresultDTO, @RequestBody MultipartFile file, HttpServletRequest request) {

        //将附件储存
        try {
            byte[] buf = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileUrl = "";
            Date date = new Date();
            requestHomeworkresultDTO.setSubmittedAt(date);
            fileUrl = "/WEB-INF/homework/" + requestHomeworkresultDTO.getHomeworkId() + "/" + requestHomeworkresultDTO.getAccount() + "/" + originalFileName;
            fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
            //向url地址存储文件
            FileUtil.writeFileToUrl(file, fileUrl);
            requestHomeworkresultDTO.setFilePath(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = homeworkresultService.findResultId(requestHomeworkresultDTO.getAccount(),requestHomeworkresultDTO.getHomeworkId());
        //已经交过，判定为更新提交结果
        if (id > 0){
            HomeworkResult homeworkResult = homeworkresultService.findById(id);
            String url = request.getSession().getServletContext().getRealPath(homeworkResult.getFilePath());
            Path path = Paths.get(url);
            try {
                //删除原附件
                Files.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            homeworkresultService.modify(requestHomeworkresultDTO);
        }
        else {
            homeworkresultService.add(requestHomeworkresultDTO);
        }
        return new ResponseVO("200","success");
    }

    //下载附件
    @RequestMapping(value = "/download")
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

}
