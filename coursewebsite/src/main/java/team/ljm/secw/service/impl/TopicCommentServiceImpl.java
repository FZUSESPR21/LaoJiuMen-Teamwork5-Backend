package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.TopicComment;
import team.ljm.secw.mapper.TopicCommentMapper;
import team.ljm.secw.service.ITopicCommentService;

import java.util.List;

@Service
public class TopicCommentServiceImpl implements ITopicCommentService {

    @Autowired
    private TopicCommentMapper topicCommentMapper;

    @Override
    public List<TopicComment> findTopicCommentListByTopicId(String topicId) {
        return topicCommentMapper.selectTopicCommentListByTopicId(topicId);
    }

    @Override
    public int removeTopicCommentById(int id) {
        return topicCommentMapper.deleteTopicCommentById(id);
    }

    @Override
    public int addTopicComment(TopicComment topicComment) {
        return topicCommentMapper.insertTopicComment(topicComment);
    }

    @Override
    public int modifyTopicCommentNumByTopicId(int id) {
        return topicCommentMapper.updateTopicCommentNumByTopicId(id);
    }

    @Override
    public int removeTopicCommentByTopicId(int topicId) {
        return topicCommentMapper.deleteTopicCommentByTopicId(topicId);
    }


}
