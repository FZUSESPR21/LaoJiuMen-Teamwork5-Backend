package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.Student;
import team.ljm.secw.mapper.HomeworkMapper;
import team.ljm.secw.service.IHomeworkService;

import java.util.List;

@Service("HomeworkService")
public class HomeworkServiceImpl implements IHomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public int add(Homework homework) {
        return homeworkMapper.insert(homework);
    }

    @Override
    public List<Homework> findAll() {
        List<Homework> list = homeworkMapper.selectList();
        return list;
    }

    @Override
    public List<Student> findStudentListByClazzId(int id) {
        List<Student> list = homeworkMapper.selectStudentListByClazzId(id);
        return list;
    }

    @Override
    public List<Homework> findListByClazzId(int id) {
        List<Homework> list = homeworkMapper.selectList();
        return list;
    }

    @Override
    public Homework findById(int id) {
        return homeworkMapper.selectById(id);
    }

    @Override
    public int modify(Homework homework) {
        return homeworkMapper.update(homework);
    }

    @Override
    public int remove(int id) {
        return homeworkMapper.deleteById(id);
    }
}
