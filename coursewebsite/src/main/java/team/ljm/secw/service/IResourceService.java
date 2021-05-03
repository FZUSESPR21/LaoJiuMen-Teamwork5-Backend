package team.ljm.secw.service;

import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface IResourceService {
    void add(Resource resource);
    List<Resource> findAll();
    Resource findById(int id);
    int remove(int id);
}
