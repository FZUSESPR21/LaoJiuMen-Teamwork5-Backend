package team.ljm.secw.mapper;

import team.ljm.secw.entity.HomeworkResult;

import java.util.List;

public interface HomeworkresultMapper {
    List<HomeworkResult> selectListById(int id);
    HomeworkResult selectById(int id);
    HomeworkResult selectByHwIdStId(HomeworkResult homeworkResult);
    //获取成绩，需要学生id与作业id，同时用于提交状态判断
    int selectScore (HomeworkResult homeworkResult);
    int insert(HomeworkResult homeworkResult);
    int updateToSubmit(HomeworkResult homeworkResult);
    int updateToCorrect(HomeworkResult homeworkResult);
    int delete(int id);
}
