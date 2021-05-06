package team.ljm.secw.mapper;

import team.ljm.secw.entity.Notification;

import java.util.List;

public interface NotificationMapper {

    List<Notification> selectListByClazzId(Notification notification);

    int insert(Notification notification);

    int update(Notification notification);

    int delete(Notification notification);

    Notification selectById(Notification notification);
}
