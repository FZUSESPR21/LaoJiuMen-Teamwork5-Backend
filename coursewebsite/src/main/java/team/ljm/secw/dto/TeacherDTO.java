package team.ljm.secw.dto;

import java.util.List;

public class TeacherDTO {

    private int id;
    private String account;
    private String teacherName;
    private List<ClazzDTO> clazzInfo;

    public TeacherDTO(int id, String account, String teacherName, List<ClazzDTO> clazzInfo) {
        this.id = id;
        this.account = account;
        this.teacherName = teacherName;
        this.clazzInfo = clazzInfo;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", clazzInfo=" + clazzInfo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<ClazzDTO> getClazzInfo() {
        return clazzInfo;
    }

    public void setClazzInfo(List<ClazzDTO> clazzInfo) {
        this.clazzInfo = clazzInfo;
    }
}
