package team.ljm.secw.service;

import team.ljm.secw.entity.TopicComment;

import java.util.List;

public interface ITopicCommentService {

    List<TopicComment> findTopicCommentListByTopicId(String topicId);

    int removeTopicCommentById(int id);

    int addTopicComment(TopicComment topicComment);

    int modifyTopicCommentNumByTopicId(int id);

    int removeTopicCommentByTopicId(int topicId);
}
