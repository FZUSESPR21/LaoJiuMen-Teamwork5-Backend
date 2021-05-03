package team.ljm.secw.service;

import team.ljm.secw.entity.Homework;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface IHomeworkService {
    void insert(Homework homework);
    List<Homework> findAll();
    Homework findById(int id);
    int modify(Homework homework);
    int remove(int id);
}
