package team.ljm.secw.mapper;

import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface HomeworkMapper {
    void insert(Homework homework);

    List<Homework> selectList();

    Homework selectById(int id);

    int update(Homework homework);

    int deleteById(int id);
}
