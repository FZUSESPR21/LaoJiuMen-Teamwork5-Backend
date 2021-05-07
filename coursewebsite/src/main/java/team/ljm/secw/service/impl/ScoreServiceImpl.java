package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.dto.HomeworkDTO;
import team.ljm.secw.dto.ScoreDTO;
import team.ljm.secw.entity.Student;
import team.ljm.secw.mapper.ScoreMapper;
import team.ljm.secw.service.IScoreService;
import team.ljm.secw.utils.ScoreExcelUtil;
import team.ljm.secw.utils.ScoreSortUtil;
import team.ljm.secw.vo.ResponseVO;

import java.util.HashMap;
import java.util.List;

@Service
public class ScoreServiceImpl implements IScoreService {

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public List<Student> findScoreListByClazzId(int clazzId) {
        return scoreMapper.selectScoreListByClazzId(clazzId);
    }

    @Override
    public HashMap<String, Integer> findHomeworkScoreByHomeworkIdAndClazzId(int homeworkId, int clazzId) {
        return ScoreSortUtil.sortScore(scoreMapper.selectHomeworkScoreByHomeworkIdAndClazzId(homeworkId, clazzId));
    }

    @Override
    public HashMap<String, Integer> findFinalScoreByClazzId(int clazzId) {
        return ScoreSortUtil.sortScore(scoreMapper.selectFinalScoreByClazzId(clazzId));
    }

    @Override
    public List<HomeworkDTO> findHomeworkIdAndTitleListByClazzId(int clazzId) {
        return scoreMapper.selectHomeworkIdAndTitleListByClazzId(clazzId);
    }

    @Override
    public List<Integer> sumAbsentNumListOrderByAccount(int clazzId) {
        return scoreMapper.countAbsentNumListOrderByAccount(clazzId);
    }

    @Override
    public List<Integer> sumNotSubmitNumListOrderByAccount(int clazzId) {
        return scoreMapper.countNotSubmitNumListOrderByAccount(clazzId);
    }

    @Override
    public List<Integer> findHomeworkScoreListOrderByAccountAndStartAt(int clazzId) {
        return scoreMapper.selectHomeworkScoreListOrderByAccountAndStartAt(clazzId);
    }

    @Override
    public List<String> findStudentAccountListByClazzId(int clazzId) {
        return scoreMapper.selectStudentAccountListByClazzId(clazzId);
    }

    @Override
    public int batchModifyScore(List<ScoreDTO> scoreList) {
        return 0;
    }

    @Override
    public ResponseVO readExcelFile(MultipartFile file) {
        List<ScoreDTO> scoreList = null;
        int updateResult = 0;
        String updateMsg = "";

        try {
            scoreList = ScoreExcelUtil.getExcelInfo(file);
            updateResult = scoreMapper.batchUpdateScore(scoreList);
            updateMsg = "成功更新：" + updateResult + "条数据";

            for(ScoreDTO s : scoreList) {
                System.out.println(s.toString());
            }

            return new ResponseVO("200", updateMsg);
        } catch (Exception e) {
            e.printStackTrace();
            updateMsg = "接收excel表格中的数据失败！";
            System.err.println(updateMsg);
            return new ResponseVO("200", updateMsg);
        }
    }
}
