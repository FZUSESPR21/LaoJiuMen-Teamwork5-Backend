package team.ljm.secw.service.impl;

import org.junit.Assert;
import org.junit.Test;
import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.Student;
import team.ljm.secw.service.AttendanceService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AttendanceServiceImplTest {

    @Test
    public void findAllAttendance() {
        List<Attendance> attendances= new ArrayList<Attendance>();
        Attendance attendance=new Attendance();
        attendance.setId(2);
        attendance.setClazzId(3);
        attendance.setAttendanceName("hey");
        attendance.setIssuer("cpz");
        attendances.add(attendance);
        Student student=new Student();
        student.setClazzId(3);
        student.setId(1);
        AttendanceService attendanceService=new AttendanceServiceImpl();
        Assert.assertEquals(attendanceService.findAllAttendance(student),attendances);
    }

    @Test
    public void insertStuAttendance() {
    }

    @Test
    public void updateResult() {
    }

    @Test
    public void findTeacherAttendance() {
    }

    @Test
    public void findStuResult() {
    }

    @Test
    public void releaseAttendance() {
    }

    @Test
    public void updateEndAt() {
    }
}