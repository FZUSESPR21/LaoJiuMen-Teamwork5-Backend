package team.ljm.secw.service;

import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.dto.HomeworkDTO;
import team.ljm.secw.dto.ScoreDTO;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.entity.Student;
import team.ljm.secw.vo.ResponseVO;

import java.util.HashMap;
import java.util.List;

public interface IScoreService {

    List<Student> findScoreListByClazzId(int clazzId);

    HashMap<String, Integer> findHomeworkScoreByHomeworkIdAndClazzId(int homeworkId, int clazzId);

    HashMap<String, Integer> findFinalScoreByClazzId(int clazzId);

    List<HomeworkDTO> findHomeworkIdAndTitleListByClazzId(int clazzId);

    List<Integer> sumAbsentNumListOrderByAccount(int clazzId);

    List<Integer> sumNotSubmitNumListOrderByAccount(int clazzId);

    List<Integer> findHomeworkScoreListOrderByAccountAndStartAt(int clazzId);

    List<String> findStudentAccountListByClazzId(int clazzId);

    int batchModifyScore(List<ScoreDTO> scoreList);

    ResponseVO readExcelFile(MultipartFile file);

    int modifyScore(ScoreDTO scoreDTO);

    ScoreDTO findScoreByStudentId(int id);

}

