package team.ljm.secw.mapper;

import team.ljm.secw.dto.ClazzDTO;
import team.ljm.secw.entity.Clazz;

import java.util.List;

public interface ClazzMapper {

    List<ClazzDTO> selectNameListByTeacherId(int teacherId);

    int insert(Clazz clazz);

    Clazz selectScoreRuleByClazzId(int clazzId);

    int updateScoreRule(Clazz clazz);
}
