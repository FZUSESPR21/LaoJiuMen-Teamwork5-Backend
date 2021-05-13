package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.mapper.StudentMapper;
import team.ljm.secw.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public int sum(int clazzId) {
        return studentMapper.count(clazzId);
    }
}
