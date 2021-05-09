package team.ljm.secw.mapper;

import team.ljm.secw.entity.TopicComment;

import java.util.List;

public interface TopicCommentMapper {
    public int deleteComment(Integer id);
    public int insertComment(TopicComment topicComment);
}
