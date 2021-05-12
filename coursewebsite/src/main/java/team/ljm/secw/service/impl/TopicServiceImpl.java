package team.ljm.secw.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ljm.secw.entity.Topic;
import team.ljm.secw.mapper.TopicMapper;
import team.ljm.secw.service.TopicService;

import java.util.List;
@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;
    @Override
    public List<Topic> findTopicByTitle(String title,Integer currentPage) {
        PageHelper.startPage(currentPage, 10);
         return this.topicMapper.findTopicByTitle(title);
    }

    @Override
    public List<Topic> findTopicByAccount(String account,Integer currentPage) {
        PageHelper.startPage(currentPage, 10);
        return this.topicMapper.findTopicByAccount(account);
    }

    @Override
    public List<Topic> findAllTopic(Integer currentPage) {
        PageHelper.startPage(currentPage, 10);
        return this.topicMapper.findAllTopic();
    }

    @Override
    public Topic findTopicComment(Integer id,Integer currentPage) {
        PageHelper.startPage(currentPage, 10);
        return this.topicMapper.findTopicComment(id);
    }

    @Override
    public int insertTopic(Topic topic) {
        return this.topicMapper.insertTopic(topic);
    }

    @Override
    public int deleteTopic(Integer id) {
        return this.topicMapper.deleteTopic(id);
    }

}
