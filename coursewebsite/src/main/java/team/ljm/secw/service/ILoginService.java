package team.ljm.secw.service;

import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.Teacher;

public interface ILoginService {

    Student findStudentByAccount(String account);

    Teacher findTeacherByAccount(String account);

    String findRoles(String type);

}
