package team.ljm.secw.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import team.ljm.secw.entity.Attendance;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.StudentVo;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class AttendanceMapperTest {
    @Test
    public void findtest() throws  Exception{
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        Student student=new Student();
        student.setAccount("5");
        student.setClazzId(1);
        StudentVo studentVo=new StudentVo();
        studentVo.setStudent(student);
        List<Attendance> list=sqlSession.selectList("mapper.AttendanceMapper.findAllAttendance",studentVo);
        for (Attendance product:list
        ) {
            System.out.println(product.toString());
        }

        sqlSession.close();
    }

}