package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ljm.secw.entity.TopicComment;
import team.ljm.secw.mapper.TopicCommentMapper;
import team.ljm.secw.service.TopicCommentService;
import team.ljm.secw.service.TopicService;
@Service
@Transactional
public class TopicCommentServiceImpl implements TopicCommentService {
    @Autowired
    private TopicCommentMapper topicCommentMapper;
    @Override
    public int deleteComment(Integer id) {
        int rows=topicCommentMapper.deleteComment(id);
        return rows;
    }

    @Override
    public int insertComment(TopicComment topicComment) {
        int rows=topicCommentMapper.insertComment(topicComment);
        return rows;
    }
}
