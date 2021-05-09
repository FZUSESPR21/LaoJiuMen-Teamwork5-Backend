package team.ljm.secw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AttendanceResult {


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date attendedAt;

    public Date getAttendedAt() {
        return attendedAt;
    }

    public void setAttendedAt(Date attendedAt) {
        this.attendedAt = attendedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    private int id;

    private int attendanceId;
    private int studentId;

    @Override
    public String toString() {
        return "AttendanceResult{" +
                "attendedAt=" + attendedAt +
                ", id=" + id +
                ", attendanceId=" + attendanceId +
                ", studentId=" + studentId +
                '}';
    }
}
