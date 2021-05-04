package team.ljm.secw.mapper;

import org.apache.ibatis.annotations.Param;
import team.ljm.secw.dto.HomeworkresultDTO;
import team.ljm.secw.entity.HomeworkResult;

public interface StuhwrsMapper {

    void insert(HomeworkresultDTO homeworkresultDTO);

    //检测是否已经提交过
    int selectByAccount(@Param("account")String account, @Param("homeworkId")int homeworkId);

    int delete(HomeworkresultDTO homeworkresultDTO);
}
