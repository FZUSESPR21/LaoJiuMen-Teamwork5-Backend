package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Resource;
import team.ljm.secw.mapper.FileMapper;
import team.ljm.secw.service.UploadService;

import java.util.List;

@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FileMapper filemapper;

    public void uploadFile(Resource resource) {
        // TODO Auto-generated method stub
        try {
            filemapper.uploadFileDao(resource);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Resource> allFile() {
        List<Resource> list = filemapper.selectList();
        return list;
    }

    public Resource selectById(int id){
        Resource resource = filemapper.selectById(id);
        return resource;
    }

    public int deleteById(int id) {
        return filemapper.deleteById(id);
    }
}
