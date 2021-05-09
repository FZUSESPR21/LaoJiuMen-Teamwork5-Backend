package team.ljm.secw.service;

import team.ljm.secw.entity.TopicComment;

public interface TopicCommentService {
    public int deleteComment(Integer id);
    public int insertComment(TopicComment topicComment);
}
