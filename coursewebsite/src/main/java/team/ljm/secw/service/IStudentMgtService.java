package team.ljm.secw.service;

import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.entity.Student;
import team.ljm.secw.vo.ResponseVO;

import java.util.HashMap;
import java.util.List;

public interface IStudentMgtService {

    ResponseVO readExcelFile(MultipartFile file);

    List<Student> findStudentListByClazzId(int clazzId);

    int add(Student student);

    int modify(Student student);

    int remove(Student student);

    int removeList(List<Student> studentList);

}
