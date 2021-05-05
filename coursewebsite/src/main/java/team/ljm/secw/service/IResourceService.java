package team.ljm.secw.service;

import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface IResourceService {
    void add(Resource resource);
    List<Resource> findAll();
    List<Resource> findListByClazzId(int id);
    public List<Resource> findOtherListByClazzId(int id);
    Resource findById(int id);
    int modifyOtherResource (Resource resource);
    int remove(int id);
}
