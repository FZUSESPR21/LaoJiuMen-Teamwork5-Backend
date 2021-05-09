package team.ljm.secw.service;

import team.ljm.secw.entity.Topic;

import java.util.List;

public interface TopicService {
    public List<Topic> findTopicByTitle(String title);
    public List<Topic> findTopicByAccount(String account);
    public List<Topic> findAllTopic();
    public Topic findTopicComment(Integer id);
    public int insertTopic(Topic topic);
    public int deleteTopic(Integer id);
}
