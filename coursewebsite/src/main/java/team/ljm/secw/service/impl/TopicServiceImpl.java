package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Topic;

import team.ljm.secw.mapper.TopicMapper;
import team.ljm.secw.service.ITopicService;

import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> findTopicList() {
        return topicMapper.selectTopicList();
    }

    @Override
    public int addTopic(Topic topic) {
        return topicMapper.insertTopic(topic);
    }

    @Override
    public List<Topic> findMyTopicList(String account) {
        return topicMapper.selectMyTopicList(account);
    }

    @Override
    public int removeTopicById(int id) {
        return topicMapper.deleteTopicById(id);
    }

    @Override
    public List<Topic> findTopicListByTitle(String title) {
        return topicMapper.selectTopicListByTitle("'%" + title + "%'");
    }

    @Override
    public List<Topic> findTopicListByAccount(String account) {
        return topicMapper.selectTopicListByAccount(account);
    }

}
