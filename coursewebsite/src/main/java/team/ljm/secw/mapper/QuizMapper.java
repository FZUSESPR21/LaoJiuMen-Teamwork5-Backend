package team.ljm.secw.mapper;

import team.ljm.secw.entity.Clazz;

public interface QuizMapper {
    public int insertQuiz(Clazz clazz);
    public String findQuiz(Integer id);
    public int updateQuiz(Clazz clazz);
}
