package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import team.ljm.secw.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    void insert(Resource resource);

    //@Select("select * from t_resource")
    List<Resource> selectList();

    Resource selectById(int id);

    Resource selectByName(Resource resource);

    List<Resource> selectListByClazzId(@Param("id") int id, @Param("type") int type);

    int updateDownload(int id);

    int update(Resource resource);

    int delete(int id);

}
