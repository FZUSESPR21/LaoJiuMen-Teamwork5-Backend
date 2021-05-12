package team.ljm.secw.service;

import team.ljm.secw.entity.Topic;

import java.util.List;

public interface TopicService {
    public List<Topic> findTopicByTitle(String title,Integer currentPage);
    public List<Topic> findTopicByAccount(String account,Integer currentPage);
    public List<Topic> findAllTopic(Integer currentPage);
    public Topic findTopicComment(Integer id,Integer currentPage);
    public int insertTopic(Topic topic);
    public int deleteTopic(Integer id);
}
