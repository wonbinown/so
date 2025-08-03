package login;

public class LoginDTO {
    private String username;
    private String password;

    // 기본 생성자
    public LoginDTO() {}

    // getter / setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
