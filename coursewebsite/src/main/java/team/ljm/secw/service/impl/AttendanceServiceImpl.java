package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.AttendanceResult;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;
import team.ljm.secw.mapper.AttendanceMapper;
import team.ljm.secw.service.AttendanceService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Override
    public List<Attendance> findAllAttendance(Student student) {
        StudentVo studentVo=new StudentVo();
        studentVo.setStudent(student);
        System.out.println(student);
        List<Attendance> attendances= attendanceMapper.findAllAttendance(studentVo);
        List<AttendanceResult> attendanceResults=attendanceMapper.findResult(student.getId());
        for(int i=0;i<attendances.size();i++){
            attendances.get(i).setResult(false);
           for (int j=0;j<attendanceResults.size();j++){
               if(attendances.get(i).getId()==attendanceResults.get(j).getAttendanceId()){
                   attendances.get(i).setResult(true);
               }
           }
            Date startAt=attendances.get(i).getStartAt();
            Date endAt=attendances.get(i).getEndAt();
            if(startAt!=null&&endAt!=null) {
                int bigger = startAt.compareTo(endAt);
                if (bigger >= 0) {
                    attendances.get(i).setState(false);
                } else {
                    attendances.get(i).setState(true);
                }
            }
        }
        return attendances;
    }

    @Override
    public int insertStuAttendance(AttendanceResult attendanceResult) {
        Date d=new Date();
        System.out.println(d);
                /*//创建一个格式化对象
                SimpleDateFormat sdf=new SimpleDateFormat();
                System.out.println(sdf);*/
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化为日期/时间字符串
        String cc=sdf.format(d);
        System.out.println(cc);
        attendanceResult.setAttendedAt(d);
        System.out.println(attendanceResult.getAttendanceId());
        return attendanceMapper.insertStuAttendance(attendanceResult);
    }



}
