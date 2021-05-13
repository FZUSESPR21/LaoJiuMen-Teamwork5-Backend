package team.ljm.secw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.ljm.secw.entity.Notification;
import team.ljm.secw.entity.Student;
import team.ljm.secw.service.INotificationService;
import team.ljm.secw.vo.ResponseVO;

import java.util.List;

//@CrossOrigin
@Controller
public class NotificationController {

    @Autowired
    INotificationService notificationService;

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/notice/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO showNoticeListByClazzId(@RequestParam(value = "clazzId") Integer clazzId,
                                              @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        Notification notification = new Notification();
        notification.setClazzId(clazzId);
        PageHelper.startPage(pn, 5);
        List<Notification> notificationList = notificationService.findNotificationListByClazzId(notification);
        PageInfo<Notification> pageInfo = new PageInfo<>(notificationList, 5);
        return new ResponseVO("200", "", pageInfo);
    }

    @RequiresRoles(value={"student","teacher"}, logical = Logical.OR)
    @RequestMapping(value = "/notice/search", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO showNoticeById(@RequestBody Notification notification) {
        return new ResponseVO("200", "", notificationService.findNotificationById(notification));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/notice/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO addNotice(@RequestBody Notification notification) {
        return new ResponseVO("200", "", notificationService.add(notification));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/notice/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateNotice(@RequestBody Notification notification) {
        return new ResponseVO("200", "", notificationService.modify(notification));
    }

    @RequiresRoles("teacher")
    @RequestMapping(value = "/teacher/notice/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO removeNotice(@RequestBody Notification notification) {
        return new ResponseVO("200", "", notificationService.remove(notification));
    }

}
