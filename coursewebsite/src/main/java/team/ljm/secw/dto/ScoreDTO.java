package team.ljm.secw.dto;

public class ScoreDTO {

    private String account;
    private int totalScore;
    private int writtenScore;
    private int usualScore;

    @Override
    public String toString() {
        return "ScoreDTO{" +
                "account='" + account + '\'' +
                ", totalScore=" + totalScore +
                ", writtenScore=" + writtenScore +
                ", usualScore=" + usualScore +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public int getUsualScore() {
        return usualScore;
    }

    public void setUsualScore(int usualScore) {
        this.usualScore = usualScore;
    }
}
