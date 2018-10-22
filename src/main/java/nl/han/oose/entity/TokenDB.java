package nl.han.oose.entity;

public class TokenDB {

    private String token;
    private int user;
    private String dateString;

    public TokenDB(String token, int user, String dateString) {
        this.token = token;
        this.user = user;
        this.dateString = dateString;
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

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
