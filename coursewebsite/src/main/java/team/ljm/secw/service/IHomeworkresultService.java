package team.ljm.secw.service;

import team.ljm.secw.dto.HomeworkresultDTO;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.HomeworkResult;

import java.util.List;

public interface IHomeworkresultService {
    List<HomeworkResult> findListByHwid(int id);
    HomeworkResult findById(int id);
    void add(HomeworkresultDTO homeworkresultDTO);
    int modify(HomeworkresultDTO homeworkresultDTO);
    int remove(HomeworkresultDTO homeworkresultDTO);
}
