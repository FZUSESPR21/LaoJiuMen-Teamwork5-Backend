package team.ljm.secw.mapper;

import team.ljm.secw.entity.Topic;

import java.util.List;

public interface TopicMapper {

    List<Topic> selectTopicList();

    int insertTopic(Topic topic);

    List<Topic> selectMyTopicList(String account);

    int deleteTopicById(int id);

    List<Topic> selectTopicListByTitle(String title);

    List<Topic> selectTopicListByAccount(String account);

}
