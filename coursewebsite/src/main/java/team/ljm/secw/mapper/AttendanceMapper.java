package team.ljm.secw.mapper;

import team.ljm.secw.dto.AttendanceDTO;
import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.AttendanceResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;

import java.util.List;

public interface AttendanceMapper {
    public List<Attendance> findAllAttendance(Integer clazzId);
    public int insertStuAttendance(AttendanceResult attendanceResult);
    public List<AttendanceResult> findResult(Integer studentid);
    public int updateResult(AttendanceResult attendanceResult);
    public List<AttendanceResult> findStuResult(Integer attendanceId);
    public List<Student> findAllStu(Integer clazzId);
    public int insertAttendance(AttendanceDTO attendance);
    public int updateEndAt(AttendanceDTO attendance);
}
