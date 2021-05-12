package team.ljm.secw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.PaperSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.dto.ResourceDTO;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.service.IResourceService;
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
import java.io.IOException;


@Controller
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    //所有资源，教师，上传,注意:由于文件存放路径是由教师id，班级id，构成，若同老师上传同名文件至同一个班级将造成覆盖
    @RequestMapping("/teacher/resource/upload")
    @ResponseBody
    public ResponseVO upload(@ModelAttribute ResourceDTO requestResource, HttpServletRequest request, Model model) {
        try {
            MultipartFile file = requestResource.getFile();
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
            Resource resource = new Resource();
            resource.setTeacherId(requestResource.getTeacherId());
            resource.setClazzId(requestResource.getClazzId());
            resource.setType(requestResource.getType());
            resource.setResourceName(originalFileName);
            resource.setFilePath(fileUrl);
            Date date = new Date();
            resource.setUploadedAt(date);
            resource.setDownloads(0);
            Resource resource1 = resourceService.findByName(resource);
            System.out.println(resource1);
            if (resource1.getResourceName()==null)
            resourceService.add(resource);
            else resourceService.modifyResource(resource);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseVO("200","success");
    }

    //学习计划，教师，更新
    @RequestMapping("/teacher/resource/update_other")
    @ResponseBody
    public ResponseVO submitOther(@ModelAttribute ResourceDTO requestResource, HttpServletRequest request, Model model) {
        Resource resource0 = resourceService.findById(requestResource.getId());
        String url = resource0.getFilePath();
        Path path = Paths.get(url);
        try {
            //删除原附件
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MultipartFile file = requestResource.getFile();
            String originalFileName = file.getOriginalFilename();
            String fileUrl = "";
            fileUrl = "/WEB-INF/other/学习计划/" + requestResource.getTeacherId() +"/" + requestResource.getClazzId() + "/" + originalFileName;
            fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
            //向url地址存储文件
            FileUtil.writeFileToUrl(file, fileUrl);
            Resource resource = new Resource();
            resource.setTeacherId(requestResource.getTeacherId());
            resource.setClazzId(requestResource.getClazzId());
            resource.setType(requestResource.getType());
            resource.setResourceName(originalFileName);
            resource.setFilePath(fileUrl);
            Date date = new Date();
            resource.setUploadedAt(date);
            resource.setDownloads(0);
            resource.setType(2);
            resourceService.modifyResource(resource);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseVO("200","success");
    }

    //课程资源，教师，全部
    @RequestMapping("/teacher/resource/real_all")
    @ResponseBody
    public ResponseVO allFile(){
        List<Resource> list = resourceService.findAll();
        return new ResponseVO("200","success",list);
    }

    //课程资源，教师按班级查
    @RequestMapping("/teacher/resource/all")
    @ResponseBody
    public ResponseVO searchFileByClazzId(@RequestParam("clazzId") int clazzId,
                                          @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<Resource> list = resourceService.findListByClazzId(clazzId);
        PageInfo<Resource> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200","success",pageInfo);
    }

    //课程资源，学生按班级查
    @RequestMapping("/student/resource/all")
    @ResponseBody
    public ResponseVO classFile(@RequestParam("clazzId") int clazzId,
                                @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<Resource> list = resourceService.findListByClazzId(clazzId);
        PageInfo<Resource> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200","success",pageInfo);
    }

    //课程其他资源，按班级查
    @RequestMapping("/resource/other")
    @ResponseBody
    public ResponseVO otherFile(@RequestParam("clazzId") int clazzId,
                                @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<Resource> list = resourceService.findOtherListByClazzId(clazzId);
        PageInfo<Resource> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200","success",pageInfo);
    }

    //学习计划，按班级查
    @RequestMapping("/resource/plan")
    @ResponseBody
    public ResponseVO planFile(@RequestParam("clazzId") int clazzId,
                               @RequestParam(value = "pn",defaultValue = "1") int pn){
        PageHelper.startPage(pn,5);
        List<Resource> list = resourceService.findPlanListByClazzId(clazzId);
        PageInfo<Resource> pageInfo = new PageInfo<>(list,5);
        return new ResponseVO("200","success",pageInfo);
    }

    //课程资源，按单个id查
    @RequestMapping("/resource/search")
    @ResponseBody
    public ResponseVO searchById(@RequestBody Resource requestResource){
        int id = requestResource.getId();
        Resource resource = resourceService.findById(id);
        return new ResponseVO("200","success",resource);
    }

    //课程资源，教师，删除
    @RequestMapping("/teacher/resource/delete")
    @ResponseBody
    public ResponseVO delete(@RequestBody Resource requestResource){
        int id = requestResource.getId();
        resourceService.remove(id);
        return new ResponseVO("200","success");
    }

    //所有资源，下载
    @RequestMapping(value = "/resource/download")
    public void download(HttpServletRequest request, HttpServletResponse response ,@RequestBody Resource requestResource){
        try {
            String filePath =  requestResource.getFilePath();
            File file = new File(filePath);//如果文件存在的话
            resourceService.modifyDownload(requestResource.getId());
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
/*
    //所有资源，教师，上传test
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseVO uploadtest(@ModelAttribute ResourceDTO requestResource, HttpServletRequest request, Model model) {
        try {
            MultipartFile file = requestResource.getFile();
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
            Resource resource = new Resource();
            resource.setTeacherId(requestResource.getTeacherId());
            resource.setClazzId(requestResource.getClazzId());
            resource.setType(requestResource.getType());
            resource.setResourceName(originalFileName);
            resource.setFilePath(fileUrl);
            Date date = new Date();
            System.out.println(date);
            resource.setUploadedAt(date);
            resource.setDownloads(0);
            Resource resource1 = resourceService.findByName(resource);
            if (resource1==null)
                resourceService.add(resource);
            else {
                resource.setId(resource1.getId());
                resourceService.modifyResource(resource);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseVO("200","success");
    }

    //所有资源，下载test
    @RequestMapping(value = "/download")
    public void downloadtest(HttpServletRequest request, HttpServletResponse response){
        try {
            Resource requestResource = resourceService.findById(8);
            System.out.println(requestResource);
            resourceService.modifyDownload(requestResource.getId());
            String filePath =  requestResource.getFilePath();//request.getSession().getServletContext().getRealPath(requestResource.getFilePath());
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
    }*/
}
