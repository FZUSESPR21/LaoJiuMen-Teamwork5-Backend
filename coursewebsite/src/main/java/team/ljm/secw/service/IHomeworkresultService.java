package team.ljm.secw.service;

import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.HomeworkResult;

import java.util.List;

public interface IHomeworkresultService {
    List<HomeworkResult> selectByHwid(int id);
}
