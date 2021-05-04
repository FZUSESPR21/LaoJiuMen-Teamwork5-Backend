package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.service.IHomeworkService;
import team.ljm.secw.vo.ResponseVO;

import java.util.List;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private IHomeworkService homeworkService;

    @RequestMapping("/teacher/all")
    @ResponseBody
    public ResponseVO allHomework(){
        List<Homework> list = homeworkService.findAll();
        return new ResponseVO("200","success",list);
    }

    @RequestMapping("/teacher/list_search")
    @ResponseBody
    public ResponseVO myClassHomework(@RequestBody int clazzId) {
        List<Homework> list = homeworkService.findListByClazzId(clazzId);
        return new ResponseVO("200", "success", list);
    }

    @RequestMapping("/teacher/add")
    @ResponseBody
    public ResponseVO addHomework(@RequestBody Homework requestHomework){
        homeworkService.add(requestHomework);
        return new ResponseVO("200","success");
    }

    @RequestMapping("/teacher/update")
    @ResponseBody
    public ResponseVO updateHomework(@RequestBody Homework requestHomework){
        int rel = homeworkService.modify(requestHomework);
        ResponseVO response = new ResponseVO("200","success");
        return response;
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseVO searchById(@RequestBody Homework requestHomework){
        int id = requestHomework.getId();
        Homework homework = homeworkService.findById(id);
        return new ResponseVO("200","success",homework);
    }

    @RequestMapping("/teacher/delete")
    @ResponseBody
    public ResponseVO delete(@RequestBody Homework requestHomework){
        int id = requestHomework.getId();
        int rel = homeworkService.remove(id);
        ResponseVO response = new ResponseVO("200","success");
        return response;
    }

    @RequestMapping("/student/all")
    @ResponseBody
    public ResponseVO classHomework(@RequestBody int clazzId) {
        List<Homework> list = homeworkService.findListByClazzId(clazzId);
        return new ResponseVO("200", "success", list);
    }
}
