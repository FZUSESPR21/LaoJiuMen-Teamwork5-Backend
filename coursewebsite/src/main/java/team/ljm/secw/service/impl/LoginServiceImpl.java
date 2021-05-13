package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.mapper.LoginMapper;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.Teacher;
import team.ljm.secw.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public Student findStudentByAccount(String account) {
        return loginMapper.selectStudentByAccount(account);
    }

    @Override
    public Teacher findTeacherByAccount(String account) {
        return loginMapper.selectTeacherByAccount(account);
    }

    @Override
    public String findRoles(String type) {
        if (type.equals("0")) {
            return "student";
        } else if (type.equals("1")) {
            return "teacher";
        } else {
            return "guest";
        }
    }

}
