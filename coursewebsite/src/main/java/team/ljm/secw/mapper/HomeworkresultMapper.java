package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Param;
import team.ljm.secw.dto.HomeworkResultDTO;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.entity.Student;

import java.util.List;

public interface HomeworkresultMapper {
    List<HomeworkResult> selectListById(int id);
    HomeworkResult selectById(int id);
    HomeworkResult selectByHwIdStId(HomeworkResult homeworkResult);
    List<HomeworkResultDTO> selectInfoListById(int id);
    List<HomeworkResultDTO> selectListByStuId(int id);
    List<HomeworkResultDTO> selectByIdStatus(@Param("id") int id,@Param("score")int score);
    List<HomeworkResultDTO> selectByIdScore(@Param("id") int id,@Param("score")int score);
    Student selectStuInfo(int id);
    //获取成绩，需要学生id与作业id，同时用于提交状态判断
    Integer selectScore (HomeworkResult homeworkResult);
    int insert(HomeworkResult homeworkResult);
    int updateToSubmit(HomeworkResult homeworkResult);
    int updateToCorrect(HomeworkResult homeworkResult);
    int delete(int id);
}
