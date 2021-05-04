package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.dto.HomeworkresultDTO;
import team.ljm.secw.entity.HomeworkResult;
import team.ljm.secw.mapper.HomeworkMapper;
import team.ljm.secw.mapper.HomeworkresultMapper;
import team.ljm.secw.mapper.StuhwrsMapper;
import team.ljm.secw.service.IHomeworkresultService;

import java.util.List;

@Service("HomeworkresultService")
public class HomeworkresultServiceImpl implements IHomeworkresultService {

    @Autowired
    private HomeworkresultMapper homeworkresultMapper;

    @Autowired
    private StuhwrsMapper stuhwrsMapper;

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
    public int findResultId(String account, int homeworkId) {
        return stuhwrsMapper.selectByAccount(account,homeworkId);
    }

    @Override
    public void add(HomeworkresultDTO homeworkresultDTO) {
        homeworkresultMapper.insert(homeworkresultDTO);
        stuhwrsMapper.insert(homeworkresultDTO);
    }

    @Override
    public int modify(HomeworkresultDTO homeworkresultDTO) {
        return homeworkresultMapper.update(homeworkresultDTO);
    }

    @Override
    public int remove(HomeworkresultDTO homeworkresultDTO) {
        int rel = homeworkresultMapper.delete(homeworkresultDTO.getId());
        stuhwrsMapper.delete(homeworkresultDTO);
        return rel;
    }


}
