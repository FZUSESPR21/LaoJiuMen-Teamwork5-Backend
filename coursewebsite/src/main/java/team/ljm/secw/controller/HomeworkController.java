package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.entity.Student;
import team.ljm.secw.service.IHomeworkService;
import team.ljm.secw.service.IHomeworkresultService;
import team.ljm.secw.service.IStudentMgtService;
import team.ljm.secw.vo.ResponseVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private IHomeworkService homeworkService;

    @Autowired
    private IHomeworkresultService homeworkResultService;

    //课程作业，教师，全部
    @RequestMapping("/teacher/all")
    @ResponseBody
    public ResponseVO allHomework(){
        List<Homework> list = homeworkService.findAll();
        return new ResponseVO("200","success",list);
    }

    //课程作业，教师，按班级查
    @RequestMapping("/teacher/list_search")
    @ResponseBody
    public ResponseVO myClassHomework(@RequestBody int clazzId) {
        List<Homework> list = homeworkService.findListByClazzId(clazzId);
        return new ResponseVO("200", "success", list);
    }

    //课程作业，教师，发布新作业
    @RequestMapping("/teacher/add")
    @ResponseBody
    public ResponseVO addHomework(@RequestBody Homework requestHomework){
        homeworkService.add(requestHomework);
        requestHomework.setId(requestHomework.getId());
        List<Student> list = homeworkService.findStudentListByClazzId(requestHomework.getClazzId());
        // 加入全班同学结果，设成绩为-2
        HomeworkResult homeworkResultTemp = new HomeworkResult();
        homeworkResultTemp.setHomeworkId(requestHomework.getId());
        for (Student student : list) {
            homeworkResultTemp.setStudentId(student.getId());
            homeworkResultService.add(homeworkResultTemp);
        }
        return new ResponseVO("200","success");
    }

    //课程作业，教师，修改作业
    @RequestMapping("/teacher/update")
    @ResponseBody
    public ResponseVO updateHomework(@RequestBody Homework requestHomework){
        homeworkService.modify(requestHomework);
        return new ResponseVO("200","success");
    }

    //课程作业，教师学生，按id查
    @RequestMapping("/search")
    @ResponseBody
    public ResponseVO searchById(@RequestBody Homework requestHomework){
        int id = requestHomework.getId();
        Homework homework = homeworkService.findById(id);
        return new ResponseVO("200","success",homework);
    }

    //课程作业，教师，删除
    @RequestMapping("/teacher/delete")
    @ResponseBody
    public ResponseVO delete(@RequestBody Homework requestHomework){
        int id = requestHomework.getId();
        int rel = homeworkService.remove(id);
        ResponseVO response = new ResponseVO("200","success");
        return response;
    }

    //课程作业，学生，按班级查
    @RequestMapping("/student/all")
    @ResponseBody
    public ResponseVO classHomework(@RequestBody int clazzId) {
        List<Homework> list = homeworkService.findListByClazzId(clazzId);
        return new ResponseVO("200", "success", list);
    }

    //课程作业，学生，按班级查，开始时间要在当前时间之后
    @RequestMapping("/student/all")
    @ResponseBody
    public ResponseVO classHomeworkNow(@RequestBody int clazzId) {
        List<Homework> list = new ArrayList<>();
        Date date = new Date();
        for (Homework homework:homeworkService.findListByClazzId(clazzId)){
            if (homework.getStartAt().before(date))list.add(homework);
        }
        return new ResponseVO("200", "success", list);
    }

}
