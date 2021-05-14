package team.ljm.secw.dto;

public class VerificationDTO {

    private String type;
    private String account;
    private String email;
    private String captcha;
    private String pwd;
    private String sessionId;

    @Override
    public String toString() {
        return "VerificationDTO{" +
                "type='" + type + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", captcha='" + captcha + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
