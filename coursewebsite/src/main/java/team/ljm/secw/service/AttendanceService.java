package team.ljm.secw.service;

import team.ljm.secw.dto.AttendanceDTO;
import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.AttendanceResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;

import java.util.List;

public interface AttendanceService {
    public List<AttendanceDTO> findAllAttendance(Student student);
    public int insertStuAttendance(AttendanceResult attendanceResult);
    public int updateResult(AttendanceResult attendanceResult);
    public List<AttendanceDTO> findTeacherAttendance(Integer clazzId);
    public List<AttendanceResult> findStuResult(Integer attendanceId);
    public int releaseAttendance(AttendanceDTO attendance);
    public int updateEndAt(AttendanceDTO attendance);
}
