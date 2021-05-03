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
@RequestMapping("/homeworkresult")
public class HomeworkresultController {

    @Autowired
    private IHomeworkresultService homeworkresultService;

    //根据作业id获取结果列表
    @RequestMapping("/searchlist")
    @ResponseBody
    public ResponseVO selectByHwid(@RequestBody Homework requestHomework){
        int id = requestHomework.getId();
        List<HomeworkResult> list = homeworkresultService.selectByHwid(id);
        return new ResponseVO("200","success",list);
    }


}
