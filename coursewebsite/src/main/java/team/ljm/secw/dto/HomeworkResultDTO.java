package team.ljm.secw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HomeworkResultDTO {
    private int id;
    private String content;
    private String filePath;
    private int score;
    private String remark;
    private int homeworkId;
    private int studentId;
    private String account;
    private String studentName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submittedAt;
    @Override
    public String toString() {
        return "HomewResultDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", filePath='" + filePath + '\'' +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", homeworkId=" + homeworkId +
                ", studentId=" + studentId +
                ", account='" + account + '\'' +
                ", stuName='" + studentName + '\'' +
                ", submittedAt=" + submittedAt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public void setStudentName(String stuName) {
        this.studentName = stuName;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }
}
