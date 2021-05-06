package team.ljm.secw.service;

import team.ljm.secw.entity.Notification;

import java.util.List;

public interface INotificationService {

    List<Notification> findNotificationListByClazzId(Notification notification);

    int add(Notification notification);

    int modify(Notification notification);

    int remove(Notification notification);

    Notification findNotificationById(Notification notification);
}
