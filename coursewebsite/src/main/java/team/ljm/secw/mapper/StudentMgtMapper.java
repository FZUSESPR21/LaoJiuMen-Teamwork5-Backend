package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Param;
import team.ljm.secw.entity.Student;

import java.util.HashMap;
import java.util.List;

public interface StudentMgtMapper {

    int batchInsert(@Param("studentList") List<Student> studentList);

    List<Student> selectListByClazzId(int clazzId);

    int insert(Student student);

    int update(Student student);

    int delete(Student student);

    int batchDelete(@Param("studentList") List<Student> studentList);

}
