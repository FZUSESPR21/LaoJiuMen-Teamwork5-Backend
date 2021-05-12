package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.Topic;
import team.ljm.secw.service.TopicService;

import java.util.List;

@Controller
@RequestMapping("/Topic")
/**
 *  /category?content= &type= (title或者author,二选一)&currentPage=(默认为1,页码),话题搜索
 *  /all?currentPage=(默认为1,页码),获得所有话题
 *  /detail?id=(话题的id)&currentPage=,话题对应的全部评论
 *  /newTopic?title=话题的标题&content=话题内容
 *  /deleteTopic?id=(话题的id),删除对应的话题
 */
public class TopicController {
    @Autowired
    private TopicService topicService;
    @RequestMapping("/category")
    /**
     *
     * 搜索
     */
    @ResponseBody
    public List<Topic> getCategory(String content,String type,Model model,@RequestParam(value = "currentPage",required=false,defaultValue="1")Integer currentPage){
        List<Topic> topics=null;
        if(type.equals("title")) {
            topics=topicService.findTopicByTitle(content,currentPage);
        }
        else{
            topics=topicService.findTopicByAccount(content,currentPage);
        }
        model.addAttribute(topics);
        return topics;
    }
    @RequestMapping("/all")
    @ResponseBody
    public List<Topic> getAll(@RequestParam(value = "currentPage",required=false,defaultValue="1")Integer currentPage){
        List<Topic> topics=topicService.findAllTopic(currentPage);
        return topics;
    }
    @RequestMapping("/detail")
    @ResponseBody
    public Topic getCommentList(Integer id,@RequestParam(value = "currentPage",required=false,defaultValue="1")Integer currentPage){
        Topic topic=topicService.findTopicComment(id,currentPage);
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
