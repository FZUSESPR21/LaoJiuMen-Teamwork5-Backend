package team.ljm.secw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.entity.Student;
import team.ljm.secw.service.IClazzService;
import team.ljm.secw.service.IStudentMgtService;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class StudentMgtController {

    @Autowired
    IStudentMgtService studentMgtService;

    @Autowired
    IClazzService clazzService;


    @RequestMapping(value = "/teacher/stu_mgt/excel")
    @ResponseBody
    public ResponseVO doExcel(@RequestParam(value = "file_excel")MultipartFile file) {
        return studentMgtService.readExcelFile(file);
    }

    @RequestMapping(value = "/teacher/stu_mgt/down")
    //@ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response) {
        try {
            //String filePath = "src/main/webapp/WEB-INF/file/format/学生名单上传格式.xlsx";
            String rootPath = request.getSession().getServletContext().getRealPath("/WEB-INF/file/format");
            String filePath = rootPath + "\\学生名单上传格式.xlsx";
            System.out.println("!!!!!!!!!!!!!!!!!" + filePath);
            //封装为完整的文件路径
            File file = new File(filePath);
            //如果文件存在的话
            if (file.exists()) {
                //获取输入流
                InputStream bis = new BufferedInputStream(new FileInputStream(file));
                //假如以中文名下载的话
                String filename = "学生名单上传格式.xlsx";
                //转码，免得文件名中文乱码
                filename = URLEncoder.encode(filename, "UTF-8");
                //设置文件下载头
                response.addHeader("Content-Disposition", "attachment;filename=" + filename);
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                /*getOutputStream获取子程序的输出流*/
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                int len = 0;
                while ((len = bis.read()) != -1) {
                    out.write(len);
                    /*将缓冲区中的数据写入到输出流中*/
                    out.flush();
                }
                out.close();
                //return new ResponseVO("200", "success");
            } else {
                //下载的文件不存在
                //request.setAttribute("errorResult", "文件不存在下载失败！");
                //return new ResponseVO("500", "文件不存在！");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            //return new ResponseVO("500", "error");
        }
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/stu_mgt/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO showStudentListByClazzId(@RequestParam(value = "clazzId") Integer clazzId,
                                               @RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        PageHelper.startPage(pn, 5);
        List<Student> studentList = studentMgtService.findStudentListByClazzId(clazzId);
        PageInfo<Student> pageInfo = new PageInfo<>(studentList, 5);
        return new ResponseVO("200", "", pageInfo);
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/stu_mgt/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO addStudent(@RequestBody Student student) {
        student.setPwd("123456");
        return new ResponseVO("200", "", studentMgtService.add(student));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/stu_mgt/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateStudent(@RequestBody Student student) {
        return new ResponseVO("200", "", studentMgtService.modify(student));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/stu_mgt/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO removeStudent(@RequestBody Student student) {
        return new ResponseVO("200", "", studentMgtService.remove(student));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/stu_mgt/dlt_li", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO removeStudentList(@RequestBody List<Student> studentList) {
        System.out.println(studentList.toString());
        return new ResponseVO("200", "", studentMgtService.removeList(studentList));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/cls/add")
    @ResponseBody
    public ResponseVO addClazz(@RequestBody Clazz clazz) {
        int num = clazzService.add(clazz);
        if (num == 1) {
            return new ResponseVO("200", String.valueOf(num), clazzService.findClazzNameListByTeacherId(clazz.getTeacherId()));
        } else {
            return new ResponseVO("500", String.valueOf(num));
        }
    }

}
