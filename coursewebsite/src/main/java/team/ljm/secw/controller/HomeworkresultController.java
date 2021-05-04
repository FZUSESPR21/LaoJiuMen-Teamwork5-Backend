package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.service.IHomeworkresultService;
import team.ljm.secw.vo.ResponseVO;

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




}
