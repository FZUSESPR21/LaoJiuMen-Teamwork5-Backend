package team.ljm.secw.service;

import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface UploadService {
    void uploadFile(Resource resource);
    List<Resource> allFile();
    Resource selectById(int id);
    int deleteById(int id);
}
