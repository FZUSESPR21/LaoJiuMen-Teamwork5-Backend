package team.ljm.secw.mapper;

import team.ljm.secw.dto.HomeworkresultDTO;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.HomeworkResult;

import java.util.List;

public interface HomeworkresultMapper {
    List<HomeworkResult> selectListById(int id);
    HomeworkResult selectById(int id);
    int insert(HomeworkresultDTO homeworkresultDTO);
    int update(HomeworkresultDTO homeworkresultDTO);
    int delete(int id);
}
