package team.ljm.secw.mapper;

public interface ChangePwdMapper {

    String selectStudentEmailByAccount(String account);

    String selectTeacherEmailByAccount(String account);

}
