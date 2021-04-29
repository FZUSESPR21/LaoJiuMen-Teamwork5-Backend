package team.ljm.secw.dto;

public class LoginDTO {

    String type;
    String account;
    String password;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "type='" + type + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
