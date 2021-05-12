package team.ljm.secw.service;

import team.ljm.secw.dto.HomeworkResultDTO;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.entity.Student;

import java.util.List;

public interface IHomeworkresultService {
    List<HomeworkResult> findListByHwid(int id);
    List<HomeworkResultDTO> findInfoListById(int id);
    List<HomeworkResultDTO> findListByStuId(int id);
    HomeworkResult findById(int id);
    List<HomeworkResultDTO> findNotSub (int id);//未提交
    List<HomeworkResultDTO> findNotCor (int id);//未批改
    List<HomeworkResultDTO> findCor (int id);//已批改
    int findResultStatus(HomeworkResult homeworkResult);
    HomeworkResult findResultStu(HomeworkResult homeworkResult);
    int add(HomeworkResult homeworkResult);
    int modify(HomeworkResult homeworkResult);
    int correct(HomeworkResult homeworkResult);
    int remove(HomeworkResult homeworkResult);
    Student findStuById(int id);
}
