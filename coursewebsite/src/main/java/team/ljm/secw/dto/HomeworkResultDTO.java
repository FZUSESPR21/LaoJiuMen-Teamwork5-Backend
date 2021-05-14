package team.ljm.secw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
    private String title;
    private String homeworkContent;
    private MultipartFile file;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submittedAt;

    @Override
    public String toString() {
        return "HomeworkResultDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", filePath='" + filePath + '\'' +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", homeworkId=" + homeworkId +
                ", studentId=" + studentId +
                ", account='" + account + '\'' +
                ", studentName='" + studentName + '\'' +
                ", title='" + title + '\'' +
                ", homeworkContent='" + homeworkContent + '\'' +
                ", file=" + file +
                ", submittedAt=" + submittedAt +
                '}';
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
