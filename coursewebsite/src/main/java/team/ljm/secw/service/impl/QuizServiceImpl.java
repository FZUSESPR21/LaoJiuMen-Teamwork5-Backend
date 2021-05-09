package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.mapper.QuizMapper;
import team.ljm.secw.service.QuizService;
@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizMapper quizMapper;
    @Override
    public int insertQuiz(Clazz clazz) {
        return quizMapper.insertQuiz(clazz);
    }

    @Override
    public String findQuiz(Integer id) {
        return quizMapper.findQuiz(id);
    }

    @Override
    public int updateQuiz(Clazz clazz) {
        return quizMapper.updateQuiz(clazz);
    }

}
