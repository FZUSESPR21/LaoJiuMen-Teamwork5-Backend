package team.ljm.secw.service;

import team.ljm.secw.entity.Clazz;

public interface QuizService {
    public int insertQuiz(Clazz clazz);
    public String findQuiz(Integer id);
    public int updateQuiz(Clazz clazz);
}
