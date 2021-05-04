package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.mapper.ResourceMapper;
import team.ljm.secw.service.IResourceService;

import java.util.List;

@Service("ResourceService")
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public void add(Resource resource) {
        resourceMapper.insert(resource);
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> list = resourceMapper.selectList();
        return list;
    }

    @Override
    public List<Resource> findListByClazzId(int id) {
        List<Resource> list = resourceMapper.selectListByClazzId(id);
        return list;
    }


    @Override
    public Resource findById(int id){
        Resource resource = resourceMapper.selectById(id);
        return resource;
    }

    @Override
    public int remove(int id) {
        return resourceMapper.delete(id);
    }
}
