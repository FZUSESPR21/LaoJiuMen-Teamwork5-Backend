package team.ljm.secw.mapper;

import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.Teacher;

public interface LoginMapper {

    Student selectStudentByAccount(String account);

    Teacher selectTeacherByAccount(String account);

}
