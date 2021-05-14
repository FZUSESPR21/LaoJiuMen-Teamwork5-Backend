package team.ljm.secw.service;

import team.ljm.secw.entity.Topic;

import java.util.List;

public interface ITopicService {

    List<Topic> findTopicList();

    int addTopic(Topic topic);

    List<Topic> findMyTopicList(String account);

    int removeTopicById(int id);

    List<Topic> findTopicListByTitle(String title);

    List<Topic> findTopicListByAccount(String account);

}
