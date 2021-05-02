package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Homework;
import team.ljm.secw.mapper.HomeworkMapper;
import team.ljm.secw.service.IHomeworkService;

import java.util.List;

@Service("HomeworkService")
public class HomeworkServiceImpl implements IHomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public void insert(Homework homework) {
        try {
            homeworkMapper.insert(homework);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Homework> all() {
        List<Homework> list = homeworkMapper.selectList();
        return list;
    }

    @Override
    public Homework selectById(int id) {
        return homeworkMapper.selectById(id);
    }

    @Override
    public int update(Homework homework) {
        return homeworkMapper.update(homework);
    }

    @Override
    public int deleteById(int id) {
        return homeworkMapper.deleteById(id);
    }
}
