package team.ljm.secw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.AttendanceResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;
import team.ljm.secw.service.AttendanceService;

import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @RequestMapping("/all")
    @ResponseBody
    public List<Attendance> getAll(Student student){
        return this.attendanceService.findAllAttendance(student);
    }
    @RequestMapping("/insert")
    @ResponseBody
    public int insert(AttendanceResult attendanceResult){
        return this.attendanceService.insertStuAttendance(attendanceResult);
    }
}
