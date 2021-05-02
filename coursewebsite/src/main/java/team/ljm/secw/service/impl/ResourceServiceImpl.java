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
    public void uploadFile(Resource resource) {
        try {
            resourceMapper.uploadFileDao(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Resource> allFile() {
        List<Resource> list = resourceMapper.selectList();
        return list;
    }

    @Override
    public Resource selectById(int id){
        Resource resource = resourceMapper.selectById(id);
        return resource;
    }

    @Override
    public int deleteById(int id) {
        return resourceMapper.deleteById(id);
    }
}
