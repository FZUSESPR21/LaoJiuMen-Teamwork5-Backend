package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.ljm.secw.entity.Student;
import team.ljm.secw.mapper.StudentMgtMapper;
import team.ljm.secw.service.IStudentMgtService;
import team.ljm.secw.utils.StudentExcelUtil;
import team.ljm.secw.vo.ResponseVO;

import java.util.List;

@Service
public class StudentMgtServiceImpl implements IStudentMgtService {

    @Autowired
    StudentMgtMapper studentMgtMapper;

    @Override
    public ResponseVO readExcelFile(MultipartFile file) {
        List<Student> studentList = null;
        int insertResult = 0;
        String insertMsg = "";

        try {
            studentList = StudentExcelUtil.getExcelInfo(file);

            for (int i = 0; i < studentList.size(); i++) {
                studentList.get(i).setPwd("123456");
                studentList.get(i).setClazzId(1);
            }

            insertResult = studentMgtMapper.batchInsert(studentList);
            insertMsg = "成功插入：" + insertResult + "条数据";

            for(Student s : studentList) {
                System.out.println(s.toString());
            }

            return new ResponseVO("200", insertMsg);
        } catch (Exception e) {
            e.printStackTrace();
            insertMsg = "接收excel表格中的数据失败！";
            System.err.println(insertMsg);
            return new ResponseVO("200", insertMsg);
        }
    }

    @Override
    public List<Student> findStudentListByClazzId(int clazzId) {
        return studentMgtMapper.selectListByClazzId(clazzId);
    }

    @Override
    public int add(Student student) {
        return studentMgtMapper.insert(student);
    }

    @Override
    public int modify(Student student) {
        return studentMgtMapper.update(student);
    }

    @Override
    public int remove(Student student) {
        return studentMgtMapper.delete(student);
    }

    @Override
    public int removeList(List<Student> studentList) {
        return studentMgtMapper.batchDelete(studentList);
    }

}
