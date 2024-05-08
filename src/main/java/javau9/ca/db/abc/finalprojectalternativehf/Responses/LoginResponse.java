package javau9.ca.db.abc.finalprojectalternativehf.Responses;

public class LoginResponse {

    private String token;
    private Long expiresIn;

    public Long getExpiresIn() {
        return expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
