package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    void uploadFileDao(Resource resource)throws Exception;

    //@Select("select * from t_resource")
    List<Resource> selectList();

    Resource selectById(int id);

    int deleteById(int id);

}
