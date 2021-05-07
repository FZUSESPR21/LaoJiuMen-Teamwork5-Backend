package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import team.ljm.secw.dto.HomeworkDTO;
import team.ljm.secw.dto.ScoreDTO;
import team.ljm.secw.entity.Student;

import java.util.List;

public interface ScoreMapper {

    List<Student> selectScoreListByClazzId(int clazzId);

    List<Integer> selectHomeworkScoreByHomeworkIdAndClazzId(@Param("homeworkId") int homeworkId, @Param("clazzId") int clazzId);

    List<Integer> selectFinalScoreByClazzId(int clazzId);

    List<HomeworkDTO> selectHomeworkIdAndTitleListByClazzId(int clazzId);

    List<Integer> countAbsentNumListOrderByAccount(int clazzId);

    List<Integer> countNotSubmitNumListOrderByAccount(int clazzId);

    List<Integer> selectHomeworkScoreListOrderByAccountAndStartAt(int clazzId);

    List<String> selectStudentAccountListByClazzId(int clazzId);

    int batchUpdateScore(@Param("scoreList") List<ScoreDTO> scoreList);
}
