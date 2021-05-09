package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.TopicComment;
import team.ljm.secw.service.TopicCommentService;

@Controller
@RequestMapping("/Comment")
public class TopicCommentController {
    @Autowired
    private TopicCommentService topicCommentService;
    @RequestMapping("/deleteComment")
    @ResponseBody
    public int deleteComment(Integer id){
        return topicCommentService.deleteComment(id);
    }
    @RequestMapping("/insertComment")
    @ResponseBody
    public int insertComment(TopicComment topicComment){
        return topicCommentService.insertComment(topicComment);
    }
}
