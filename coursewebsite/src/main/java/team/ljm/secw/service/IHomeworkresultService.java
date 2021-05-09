package team.ljm.secw.service;

import team.ljm.secw.entity.HomeworkResult;

import java.util.List;

public interface IHomeworkresultService {
    List<HomeworkResult> findListByHwid(int id);
    HomeworkResult findById(int id);
    int findResultStatus(HomeworkResult homeworkResult);
    HomeworkResult findResultStu(HomeworkResult homeworkResult);
    int add(HomeworkResult homeworkResult);
    int modify(HomeworkResult homeworkResult);
    int correct(HomeworkResult homeworkResult);
    int remove(HomeworkResult homeworkResult);
}
