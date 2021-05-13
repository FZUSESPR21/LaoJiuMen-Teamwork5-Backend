package team.ljm.secw.service;

import team.ljm.secw.dto.ClazzDTO;
import team.ljm.secw.entity.Clazz;

import java.util.List;

public interface IClazzService {

    List<ClazzDTO> findClazzNameListByTeacherId(int teacherId);

    int add(Clazz clazz);

    Clazz findScoreRuleByClazzId(int clazzId);

    int modifyScoreRule(Clazz clazz);
}
