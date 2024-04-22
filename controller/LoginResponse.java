package MrcProject6.MrcProject6.controller;

public class LoginResponse {
    private String token;
    private String userNick;

    public LoginResponse(String token, String userNick) {
        this.token = token;
        this.userNick = userNick;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
}
