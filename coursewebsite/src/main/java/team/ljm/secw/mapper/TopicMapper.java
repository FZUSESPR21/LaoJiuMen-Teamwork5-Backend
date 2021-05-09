package team.ljm.secw.mapper;

import team.ljm.secw.entity.Topic;

import java.util.List;

public interface TopicMapper {
    public Topic findTopicById(Integer id);
    public List<Topic> findTopicByTitle(String title);
    public List<Topic> findTopicByAccount(String account);
    public List<Topic> findAllTopic();
    public Topic findTopicComment(Integer id);
    public int insertTopic(Topic topic);
    public int deleteTopic(Integer id);
}
