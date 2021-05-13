package team.ljm.secw.mapper;

import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.entity.Student;

import java.util.List;

public interface HomeworkMapper {

    int insert(Homework homework);

    List<Homework> selectList();

    List<Homework> selectListByClazzId(int id);

    List<Student> selectStudentListByClazzId(int id);

    Homework selectById(int id);

    int update(Homework homework);

    int deleteById(int id);
}
