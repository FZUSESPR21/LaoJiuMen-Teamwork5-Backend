package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Notification;
import team.ljm.secw.mapper.NotificationMapper;
import team.ljm.secw.service.INotificationService;

import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public List<Notification> findNotificationListByClazzId(Notification notification) {
        return notificationMapper.selectListByClazzId(notification);
    }

    @Override
    public int add(Notification notification) {
        return notificationMapper.insert(notification);
    }

    @Override
    public int modify(Notification notification) {
        return notificationMapper.update(notification);
    }

    @Override
    public int remove(Notification notification) {
        return notificationMapper.delete(notification);
    }

    @Override
    public Notification findNotificationById(Notification notification) {
        return notificationMapper.selectById(notification);
    }
}
