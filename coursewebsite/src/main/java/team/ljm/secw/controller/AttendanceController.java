package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.dto.AttendanceDTO;
import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.AttendanceResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;
import team.ljm.secw.service.AttendanceService;
import team.ljm.secw.vo.ResponseVO;

import java.util.List;

@Controller
@RequestMapping("/attendance")
/**
 * /all?clazzId= &studentId=,学生签到界面
 * /insert,不要管
 * /stuUpdate?attendeAt=,插入学生签到时间,需要传一个时间过来
 * /teacherAll?clazzId=,教师的签到首页
 * /stuList?attendanceId=,点击一个签到进去,获得学生的签到情况
 * /release?attendanceName=  &endAt(结束时间) &issuer=  &clazzId=,教师发布签到
 * /updateTime?id=   &endAt=   ,教师更改签到时间
 */
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @RequestMapping("/all")
    @ResponseBody
    public ResponseVO getAll(@RequestBody  Student student){
        return new ResponseVO("200","",this.attendanceService.findAllAttendance(student));
    }
    @RequestMapping("/insert")
    @ResponseBody
    public ResponseVO insert(@RequestBody AttendanceResult attendanceResult){
        return new ResponseVO("200","",this.attendanceService.insertStuAttendance(attendanceResult));
    }
    @RequestMapping("/teacherAll")
    @ResponseBody
    public ResponseVO teacherAll(@RequestBody Integer clazzId){
        return new ResponseVO("200","",this.attendanceService.findTeacherAttendance(clazzId));
    }
    @RequestMapping("/stuUpdate")
    @ResponseBody
    public ResponseVO stuUpdate(@RequestBody AttendanceResult attendanceResult){
        return new ResponseVO("200","",this.attendanceService.updateResult(attendanceResult));
    }
    @RequestMapping("/stuList")
    @ResponseBody
    public ResponseVO AttendenceList(@RequestBody Integer attendanceId){
        return new ResponseVO("200","",attendanceService.findStuResult(attendanceId));
    }
    @RequestMapping("/release")
    @ResponseBody
    public ResponseVO release(@RequestBody AttendanceDTO attendance){
        return new ResponseVO("200","",attendanceService.releaseAttendance(attendance));
    }
    @RequestMapping("/updateTime")
    @ResponseBody
    public ResponseVO updateTime(@RequestBody AttendanceDTO attendance){
        return new ResponseVO("200","",attendanceService.updateEndAt(attendance));
    }
}
