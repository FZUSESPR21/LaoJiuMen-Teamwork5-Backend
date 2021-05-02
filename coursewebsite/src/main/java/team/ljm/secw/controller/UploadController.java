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
import team.ljm.secw.service.UploadService;
import team.ljm.secw.service.impl.UploadServiceImpl;
import team.ljm.secw.utils.DateUtils;
import team.ljm.secw.utils.FileUtil;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/res")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/upload")
    @ResponseBody
    public ResponseVO upload(Resource requestResource, MultipartFile file, HttpServletRequest request) {
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
            fileUrl = "/" + requestResource.getTeacherId() +"/" + requestResource.getTeacherId() + "/" + time + originalFileName;
            fileUrl = request.getSession().getServletContext().getRealPath(fileUrl);
            //向url地址存储文件
            FileUtil.writeFileToUrl(file, fileUrl);
            requestResource.setResourceName(originalFileName);
            requestResource.setFilePath(fileUrl);
            Date date = new Date();
            //String dateTime = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd HH:mm:ss");
            requestResource.setUploadedAt(date);
            requestResource.setDownloads(0);
            uploadService.uploadFile(requestResource);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseVO("200","success");
    }

    @RequestMapping("/allfile")
    @ResponseBody
    public ResponseVO allfile(){
        List<Resource> list = uploadService.allFile();
        /*for (Resource resource : list) {
            System.out.println(resource);
        }*/
        return new ResponseVO("200","success",list);
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public ResponseVO selectById(int id){
        Resource resource = uploadService.selectById(id);
        return new ResponseVO("200","success",resource);
    }
}
