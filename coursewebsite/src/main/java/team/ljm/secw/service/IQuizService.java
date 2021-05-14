package team.ljm.secw.service;

import team.ljm.secw.entity.Clazz;

public interface IQuizService {

    int addQuiz(Clazz clazz);
    String findQuiz(Integer id);
    int modifyQuiz(Clazz clazz);
}
