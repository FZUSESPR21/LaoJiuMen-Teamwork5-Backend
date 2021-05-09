package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.mapper.HomeworkresultMapper;
import team.ljm.secw.service.IHomeworkresultService;

import java.util.List;

@Service("HomeworkresultService")
public class HomeworkresultServiceImpl implements IHomeworkresultService {

    @Autowired
    private HomeworkresultMapper homeworkresultMapper;

    @Override
    public List<HomeworkResult> findListByHwid(int id) {
        List<HomeworkResult> list = homeworkresultMapper.selectListById(id);
        return list;
    }

    @Override
    public HomeworkResult findById(int id) {
        return homeworkresultMapper.selectById(id);
    }

    @Override
    public int findResultStatus(HomeworkResult homeworkResult) {
        return homeworkresultMapper.selectScore(homeworkResult);
    }

    @Override
    public HomeworkResult findResultStu(HomeworkResult homeworkResult) {
        return homeworkresultMapper.selectByHwIdStId(homeworkResult);
    }

    @Override
    public int add(HomeworkResult homeworkResult) {
        return homeworkresultMapper.insert(homeworkResult);
    }

    @Override
    public int modify(HomeworkResult homeworkResult) {
        return homeworkresultMapper.updateToSubmit(homeworkResult);
    }

    @Override
    public int correct(HomeworkResult homeworkResult) {
        return homeworkresultMapper.updateToCorrect(homeworkResult);
    }

    @Override
    public int remove(HomeworkResult homeworkResult) {
        return homeworkresultMapper.delete(homeworkResult.getId());
    }


}
