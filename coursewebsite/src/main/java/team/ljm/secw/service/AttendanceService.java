package team.ljm.secw.service;

import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.AttendanceResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;

import java.util.List;

public interface AttendanceService {
    public List<Attendance> findAllAttendance(Student student);
    public int insertStuAttendance(AttendanceResult attendanceResult);
}
