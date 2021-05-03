package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.mapper.HomeworkMapper;
import team.ljm.secw.mapper.HomeworkresultMapper;
import team.ljm.secw.service.IHomeworkresultService;

import java.util.List;

@Service("HomeworkresultService")
public class HomeworkresultServiceImpl implements IHomeworkresultService {

    @Autowired
    private HomeworkresultMapper homeworkresultMapper;

    @Override
    public List<HomeworkResult> selectByHwid(int id) {
        List<HomeworkResult> list = homeworkresultMapper.selectListById(id);
        return list;
    }
}
