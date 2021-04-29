package team.ljm.secw.entity;

public class Student {
    private int id;
    private String account;
    private String studentName;
    private String pwd;
    private String email;
    private int clazzId;
    private int totalScore;
    private int writtenScore;
    private int usualScore;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", studentName='" + studentName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", clazzId=" + clazzId +
                ", totalScore=" + totalScore +
                ", writtenScore=" + writtenScore +
                ", usualScore=" + usualScore +
                '}';
    }

    public int getUsualScore() {
        return usualScore;
    }

    public void setUsualScore(int usualScore) {
        this.usualScore = usualScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getWrittenScore() {
        return writtenScore;
    }

    public void setWrittenScore(int writtenScore) {
        this.writtenScore = writtenScore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
