package team.ljm.secw.service;

import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface IResourceService {
    void add(Resource resource);
    List<Resource> findAll();
    List<Resource> findListByClazzId(int id);
    List<Resource> findOtherListByClazzId(int id);
    List<Resource> findPlanListByClazzId(int id);
    Resource findById(int id);
    Resource findByName(Resource resourceName);
    int modifyResource (Resource resource);
    int modifyDownload(int id);
    int remove(int id);
}
