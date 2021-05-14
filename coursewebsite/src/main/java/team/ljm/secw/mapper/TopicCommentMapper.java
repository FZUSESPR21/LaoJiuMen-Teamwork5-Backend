package team.ljm.secw.mapper;

import team.ljm.secw.entity.TopicComment;

import java.util.List;

public interface TopicCommentMapper {

    List<TopicComment> selectTopicCommentListByTopicId(String topicId);

    int deleteTopicCommentById(int id);

    int insertTopicComment(TopicComment topicComment);

    int updateTopicCommentNumByTopicId(int id);

    int deleteTopicCommentByTopicId(int topicId);
}
