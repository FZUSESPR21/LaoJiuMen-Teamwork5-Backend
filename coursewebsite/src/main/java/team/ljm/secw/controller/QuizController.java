package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.mapper.QuizMapper;
import team.ljm.secw.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @RequestMapping("/insert")
    @ResponseBody
    public int insert(Clazz clazz){
        return quizService.insertQuiz(clazz);
    }
    @RequestMapping("/find")
    @ResponseBody
    public String find(Integer id){
        return quizService.findQuiz(id);
    }
    @RequestMapping("/update")
    @ResponseBody
    public int update(Clazz clazz){
        return quizService.updateQuiz(clazz);
    }
}
