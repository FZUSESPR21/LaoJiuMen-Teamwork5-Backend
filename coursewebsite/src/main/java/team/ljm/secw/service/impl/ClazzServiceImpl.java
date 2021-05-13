package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.dto.ClazzDTO;
import team.ljm.secw.entity.Clazz;
import team.ljm.secw.mapper.ClazzMapper;
import team.ljm.secw.service.IClazzService;

import java.util.List;

@Service
public class ClazzServiceImpl implements IClazzService {

    @Autowired
    ClazzMapper clazzMapper;

    @Override
    public List<ClazzDTO> findClazzNameListByTeacherId(int teacherId) {
        return clazzMapper.selectNameListByTeacherId(teacherId);
    }

    @Override
    public int add(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    @Override
    public Clazz findScoreRuleByClazzId(int clazzId) {
        return clazzMapper.selectScoreRuleByClazzId(clazzId);
    }

    @Override
    public int modifyScoreRule(Clazz clazz) {
        return clazzMapper.updateScoreRule(clazz);
    }

}
