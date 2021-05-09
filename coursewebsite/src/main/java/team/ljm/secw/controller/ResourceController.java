package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.service.IResourceService;
import team.ljm.secw.utils.FileUtil;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.io.IOException;


@Controller
@RequestMapping("/res")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    //所有资源，教师，上传
    @RequestMapping("/teacher/upload")
    @ResponseBody
    public ResponseVO upload(@RequestBody Resource requestResource,@RequestBody MultipartFile file, HttpServletRequest request) {
        try {
            byte[] buf = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileUrl = "";
            if (requestResource.getType()==0)
                fileUrl = "/WEB-INF/resource/" + requestResource.getTeacherId() +"/" + requestResource.getClazzId() + "/" + originalFileName;
            else if (requestResource.getType()==1)
                fileUrl = "/WEB-INF/other/" + requestResource.getTeacherId() +"/" + requestResource.getClazzId() + "/" + originalFileName;
            else if (requestResource.getType()==2)
                fileUrl = "/WEB-INF/other/学习计划/" + requestResource.getTeacherId() +"/" + requestResource.getClazzId() + "/" + originalFileName;
            fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
            //向url地址存储文件
            FileUtil.writeFileToUrl(file, fileUrl);
            requestResource.setResourceName(originalFileName);
            requestResource.setFilePath(fileUrl);
            Date date = new Date();
            requestResource.setUploadedAt(date);
            requestResource.setDownloads(0);
            resourceService.add(requestResource);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseVO("200","success");
    }

    //学习计划，教师，更新
    @RequestMapping("/teacher/update_other")
    @ResponseBody
    public ResponseVO submitOther(@RequestBody Resource requestResource, @RequestBody MultipartFile file, HttpServletRequest request) {
        try {
            byte[] buf = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileUrl = "";
            fileUrl = "/WEB-INF/other/学习计划/" + requestResource.getTeacherId() +"/" + requestResource.getClazzId() + "/" + originalFileName;
            fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
            //向url地址存储文件
            FileUtil.writeFileToUrl(file, fileUrl);
            requestResource.setResourceName(originalFileName);
            requestResource.setFilePath(fileUrl);
            Date date = new Date();
            requestResource.setUploadedAt(date);
            requestResource.setDownloads(0);
            requestResource.setType(2);
            resourceService.modifyOtherResource(requestResource);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseVO("200","success");
    }

    //课程资源，教师，全部
    @RequestMapping("/teacher/real_all")
    @ResponseBody
    public ResponseVO allFile(){
        List<Resource> list = resourceService.findAll();
        return new ResponseVO("200","success",list);
    }

    //课程资源，教师按班级查
    @RequestMapping("/teacher/list_search")
    @ResponseBody
    public ResponseVO searchFileByClazzId(@RequestBody int id){
        List<Resource> list = resourceService.findListByClazzId(id);
        return new ResponseVO("200","success",list);
    }

    //课程资源，学生按班级查
    @RequestMapping("/student/all")
    @ResponseBody
    public ResponseVO classFile(@RequestBody int id){
        List<Resource> list = resourceService.findListByClazzId(id);
        return new ResponseVO("200","success",list);
    }

    //课程其他资源，按班级查
    @RequestMapping("/search_other")
    @ResponseBody
    public ResponseVO otherFile(@RequestBody int id){
        List<Resource> list = resourceService.findOtherListByClazzId(id);
        return new ResponseVO("200","success",list);
    }

    //学习计划，按班级查
    @RequestMapping("/search_plan")
    @ResponseBody
    public ResponseVO planFile(@RequestBody int id){
        List<Resource> list = resourceService.findPlanListByClazzId(id);
        return new ResponseVO("200","success",list);
    }

    //课程资源，按单个id查
    @RequestMapping("/search")
    @ResponseBody
    public ResponseVO searchById(@RequestBody Resource requestResource){
        int id = requestResource.getId();
        Resource resource = resourceService.findById(id);
        return new ResponseVO("200","success",resource);
    }

    //课程资源，教师，删除
    @RequestMapping("/teacher/delete")
    @ResponseBody
    public ResponseVO delete(@RequestBody Resource requestResource){
        int id = requestResource.getId();
        resourceService.remove(id);
        return new ResponseVO("200","success");
    }

    //所有资源，下载
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
