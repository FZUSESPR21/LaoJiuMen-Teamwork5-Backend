package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.entity.Student;
import team.ljm.secw.service.IStudentMgtService;
import team.ljm.secw.vo.ResponseVO;

@Controller
public class StudentMgtController {

    @Autowired
    IStudentMgtService studentMgtService;

    @RequestMapping("/teacher/stu/excel")
    @ResponseBody
    public ResponseVO doExcel(@RequestParam(value = "file_excel")MultipartFile file) {
        return studentMgtService.readExcelFile(file);
    }

    @RequestMapping("/teacher/stu/all")
    @ResponseBody
    public ResponseVO showStudentListByClazzId(@RequestBody Student student) {
        return new ResponseVO("200", "", studentMgtService.findStudentListByClazzId(student));
    }

    @RequestMapping("/teacher/stu/add")
    @ResponseBody
    public ResponseVO addStudent(@RequestBody Student student) {
        student.setPwd("123456");
        return new ResponseVO("200", "", studentMgtService.add(student));
    }

    @RequestMapping("/teacher/stu/update")
    @ResponseBody
    public ResponseVO updateStudent(@RequestBody Student student) {
        return new ResponseVO("200", "", studentMgtService.modify(student));
    }

    @RequestMapping("/teacher/stu/remove")
    @ResponseBody
    public ResponseVO removeStudent(@RequestBody Student student) {
        return new ResponseVO("200", "", studentMgtService.remove(student));
    }

}
