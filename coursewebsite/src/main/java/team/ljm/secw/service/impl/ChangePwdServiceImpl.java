package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.mapper.ChangePwdMapper;
import team.ljm.secw.service.IChangePwdService;

@Service
public class ChangePwdServiceImpl implements IChangePwdService {

    @Autowired
    ChangePwdMapper changePwdMapper;

    @Override
    public String findStudentEmailByAccount(String account) {
        return changePwdMapper.selectStudentEmailByAccount(account);
    }

    @Override
    public String findTeacherEmailByAccount(String account) {
        return changePwdMapper.selectTeacherEmailByAccount(account);
    }
}
