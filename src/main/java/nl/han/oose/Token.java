package nl.han.oose;

public class Token {

    private String token;
    private String user;

    public Token(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }
}
