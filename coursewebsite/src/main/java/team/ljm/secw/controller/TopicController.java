package team.ljm.secw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.ljm.secw.entity.Topic;
import team.ljm.secw.service.ITopicCommentService;
import team.ljm.secw.service.ITopicService;
import team.ljm.secw.vo.ResponseVO;

import java.util.List;

@Controller
public class TopicController {

    @Autowired
    ITopicService TopicService;

    @Autowired
    ITopicCommentService TopicCommentService;

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/topic/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO showTopicList(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Topic> topicList = TopicService.findTopicList();
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList, 5);
        return new ResponseVO("200", "", pageInfo);
    }

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/topic/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO addTopic(@RequestBody Topic topic) {
        return new ResponseVO("200","", TopicService.addTopic(topic));
    }

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/topic/my", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO showMyTopicList(@RequestParam("account") String account,
                                      @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Topic> topicList = TopicService.findMyTopicList(account);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList, 5);
        return new ResponseVO("200", "", pageInfo);
    }

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/topic/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO deleteTopicById(@RequestBody Topic topic) {
        int successNum = TopicService.removeTopicById(topic.getId());
        if (successNum == 1) {
            TopicCommentService.removeTopicCommentByTopicId(topic.getId());
        }
        return new ResponseVO("200", "", successNum);
    }

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/topic/srh_t", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO searchTopicListByTitle(@RequestParam("title") String title,
                                             @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Topic> topicList = TopicService.findTopicListByTitle(title);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList, 5);
        return new ResponseVO("200","", pageInfo);
    }

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/topic/srh_a", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO searchTopicListByAccount(@RequestParam("account") String account,
                                               @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Topic> topicList = TopicService.findTopicListByAccount(account);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList, 5);
        return new ResponseVO("200","", pageInfo);
    }

}
