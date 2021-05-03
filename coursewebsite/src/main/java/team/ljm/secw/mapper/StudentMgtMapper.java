package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Param;
import team.ljm.secw.entity.Student;

import java.util.List;

public interface StudentMgtMapper {

    int batchInsert(@Param("studentList") List<Student> studentList);

    List<Student> selectListByClazzId(Student student);

    int insert(Student student);

    int update(Student student);

    int delete(Student student);
}
