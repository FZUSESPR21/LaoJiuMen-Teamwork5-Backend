package team.ljm.secw.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.service.IQuizService;
import team.ljm.secw.vo.ResponseVO;

@Controller
public class QuizController {

    @Autowired
    private IQuizService QuizService;

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/quiz/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insert(@RequestBody Clazz clazz) {
        return new ResponseVO("200","", QuizService.addQuiz(clazz));
    }

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/quiz/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO find(@RequestParam("id") Integer id) {
        return new ResponseVO("200","", QuizService.findQuiz(id));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/quiz/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO update(@RequestBody Clazz clazz){
        return new ResponseVO("200","", QuizService.modifyQuiz(clazz));
    }

}
