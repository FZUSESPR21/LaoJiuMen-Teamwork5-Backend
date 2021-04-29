package team.ljm.secw.entity;

public class Teacher {
    private int id;
    private String account;
    private String teacherName;
    private String pwd;
    private String email;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
