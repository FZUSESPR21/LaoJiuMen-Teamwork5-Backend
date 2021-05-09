package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.Topic;
import team.ljm.secw.service.TopicService;

import java.util.List;

@Controller
@RequestMapping("/Topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @RequestMapping("/category")
    /**
     *
     * 搜索
     */
    @ResponseBody
    public List<Topic> getCategory(String content,String type,Model model){
        List<Topic> topics=null;
        if(type.equals("title")) {
            topics=topicService.findTopicByTitle(content);
        }
        else{
            topics=topicService.findTopicByAccount(content);
        }
        model.addAttribute(topics);
        return topics;
    }
    @RequestMapping("/all")
    @ResponseBody
    public List<Topic> getAll(){
        List<Topic> topics=topicService.findAllTopic();
        return topics;
    }
    @RequestMapping("/detail")
    @ResponseBody
    public Topic getCommentList(Integer id){
        Topic topic=topicService.findTopicComment(id);
        return topic;
    }
    @RequestMapping("/newTopic")
    @ResponseBody
    public int insertTopic(Topic topic){
        int rows=topicService.insertTopic(topic);
        return rows;
    }
    @RequestMapping("/deleteTopic")
    @ResponseBody
    public int deleteTopic(Integer id){
        int rows=topicService.deleteTopic(id);
        return rows;
    }
}
