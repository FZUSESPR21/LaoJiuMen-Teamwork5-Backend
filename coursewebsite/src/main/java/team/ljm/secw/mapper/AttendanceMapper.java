package team.ljm.secw.mapper;

import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.AttendanceResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;

import java.util.List;

public interface AttendanceMapper {
    public List<Attendance> findAllAttendance(StudentVo studentvo);
    public int insertStuAttendance(AttendanceResult attendanceResult);
    public List<AttendanceResult> findResult(Integer studentid);
}
