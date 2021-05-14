package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.mapper.QuizMapper;
import team.ljm.secw.service.IQuizService;

@Service
public class QuizServiceImpl implements IQuizService {
    @Autowired
    private QuizMapper quizMapper;

    @Override
    public int addQuiz(Clazz clazz) {
        return quizMapper.insertQuiz(clazz);
    }

    @Override
    public String findQuiz(Integer id) {
        return quizMapper.selectQuiz(id);
    }

    @Override
    public int modifyQuiz(Clazz clazz) {
        return quizMapper.updateQuiz(clazz);
    }

}
