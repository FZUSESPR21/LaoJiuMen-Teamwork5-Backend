package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    void insert(Resource resource);

    //@Select("select * from t_resource")
    List<Resource> selectList();

    Resource selectById(int id);

    List<Resource> selectListByClazzId(int id);

    int delete(int id);

}
