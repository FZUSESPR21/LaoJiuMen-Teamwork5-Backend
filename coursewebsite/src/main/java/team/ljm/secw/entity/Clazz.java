package team.ljm.secw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Clazz {

    private int id;
    private String clazzName;
    private int teacherId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    private String quizLink;
    private int usualScoreProportion;
    private int absentScore;
    private int performanceScore;
    private int quizAScore;
    private int quizBScore;
    private int quizCScore;
    private int quizDScore;
    private int quizEScore;
    private int notSubmittedScore;

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", clazzName='" + clazzName + '\'' +
                ", teacherId=" + teacherId +
                ", createdAt=" + createdAt +
                ", quizLink='" + quizLink + '\'' +
                ", usualScoreProportion=" + usualScoreProportion +
                ", absentScore=" + absentScore +
                ", performanceScore=" + performanceScore +
                ", quizAScore=" + quizAScore +
                ", quizBScore=" + quizBScore +
                ", quizCScore=" + quizCScore +
                ", quizDScore=" + quizDScore +
                ", quizEScore=" + quizEScore +
                ", notSubmittedScore=" + notSubmittedScore +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getQuizLink() {
        return quizLink;
    }

    public void setQuizLink(String quizLink) {
        this.quizLink = quizLink;
    }

    public int getUsualScoreProportion() {
        return usualScoreProportion;
    }

    public void setUsualScoreProportion(int usualScoreProportion) {
        this.usualScoreProportion = usualScoreProportion;
    }

    public int getAbsentScore() {
        return absentScore;
    }

    public void setAbsentScore(int absentScore) {
        this.absentScore = absentScore;
    }

    public int getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(int performanceScore) {
        this.performanceScore = performanceScore;
    }

    public int getQuizAScore() {
        return quizAScore;
    }

    public void setQuizAScore(int quizAScore) {
        this.quizAScore = quizAScore;
    }

    public int getQuizBScore() {
        return quizBScore;
    }

    public void setQuizBScore(int quizBScore) {
        this.quizBScore = quizBScore;
    }

    public int getQuizCScore() {
        return quizCScore;
    }

    public void setQuizCScore(int quizCScore) {
        this.quizCScore = quizCScore;
    }

    public int getQuizDScore() {
        return quizDScore;
    }

    public void setQuizDScore(int quizDScore) {
        this.quizDScore = quizDScore;
    }

    public int getQuizEScore() {
        return quizEScore;
    }

    public void setQuizEScore(int quizEScore) {
        this.quizEScore = quizEScore;
    }

    public int getNotSubmittedScore() {
        return notSubmittedScore;
    }

    public void setNotSubmittedScore(int notSubmittedScore) {
        this.notSubmittedScore = notSubmittedScore;
    }
}
