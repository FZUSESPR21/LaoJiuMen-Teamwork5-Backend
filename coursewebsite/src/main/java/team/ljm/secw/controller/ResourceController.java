package team.ljm.secw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.service.IResourceService;
import team.ljm.secw.utils.DateUtils;
import team.ljm.secw.utils.FileUtil;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;


@Controller
@RequestMapping("/res")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping("/teacher/upload")
    @ResponseBody
    public ResponseVO upload(@RequestBody Resource requestResource,@RequestBody MultipartFile file, HttpServletRequest request) {
        try {
            byte[] buf = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            //时间戳
            String time = FileUtil.createFileTimestamp();
            //文件url		/upload/1231231231231a.txt
            String fileUrl = "";
            /*指定路径
            if (requestResource.getFilePath() == null)fileUrl = "/upload/" + time + originalFileName;
            else fileUrl = "/" + requestResource.getFilePath()+ "/" + time + originalFileName;*/
            //
            fileUrl = "/WEB-INF/resource/" + requestResource.getTeacherId() +"/" + requestResource.getTeacherId() + "/" + originalFileName;
            fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
            //向url地址存储文件
            FileUtil.writeFileToUrl(file, fileUrl);
            requestResource.setResourceName(originalFileName);
            requestResource.setFilePath(fileUrl);
            Date date = new Date();
            //String dateTime = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd HH:mm:ss");
            requestResource.setUploadedAt(date);
            requestResource.setDownloads(0);
            resourceService.add(requestResource);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseVO("200","success");
    }

    @RequestMapping("/teacher/real_all")
    @ResponseBody
    public ResponseVO allFile(){
        List<Resource> list = resourceService.findAll();
        return new ResponseVO("200","success",list);
    }

    @RequestMapping("/teacher/list_search")
    @ResponseBody
    public ResponseVO searchFileByClazzId(@RequestBody int id){
        List<Resource> list = resourceService.findListByClazzId(id);
        return new ResponseVO("200","success",list);
    }

    @RequestMapping("/student/all")
    @ResponseBody
    public ResponseVO classFile(@RequestBody int id){
        List<Resource> list = resourceService.findListByClazzId(id);
        return new ResponseVO("200","success",list);
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseVO searchById(@RequestBody Resource requestResource){
        int id = requestResource.getId();
        Resource resource = resourceService.findById(id);
        return new ResponseVO("200","success",resource);
    }

    @RequestMapping("/teacher/delete")
    @ResponseBody
    public ResponseVO delete(@RequestBody Resource requestResource){
        int id = requestResource.getId();
        resourceService.remove(id);
        return new ResponseVO("200","success");
    }

    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response ,@RequestBody Resource requestResource){
        try {
            String filePath =  request.getSession().getServletContext().getRealPath(requestResource.getFilePath());
            File file = new File(filePath);//如果文件存在的话
            if (file.exists()) {//获取输入流
                InputStream bis = new BufferedInputStream(new FileInputStream(file));//假如以中文名下载的话
                String filename = requestResource.getResourceName() ;
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
