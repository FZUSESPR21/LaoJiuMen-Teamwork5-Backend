package team.ljm.secw.mapper;

import team.ljm.secw.entity.Clazz;

public interface QuizMapper {
    int insertQuiz(Clazz clazz);
    String selectQuiz(Integer id);
    int updateQuiz(Clazz clazz);
}
