package nl.han.oose.Login;

public class TokenOnlyReturnValuesForResponse {

    private String token;
    private int user;

    public TokenOnlyReturnValuesForResponse(String token, int user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getuser() {
        return user;
    }

    public void setuser(int user) {
        this.user = user;
    }
}
