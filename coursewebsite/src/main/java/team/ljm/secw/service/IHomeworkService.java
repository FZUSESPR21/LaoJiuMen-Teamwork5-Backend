package team.ljm.secw.service;

import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface IHomeworkService {
    void insert(Homework homework);
    List<Homework> all();
    Homework selectById(int id);
    int update(Homework homework);
    int deleteById(int id);
}
