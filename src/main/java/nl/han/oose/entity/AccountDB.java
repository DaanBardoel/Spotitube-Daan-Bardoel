package nl.han.oose.entity;

public class AccountDB {

    private int userId;
    private String user;
    private String password;

    public AccountDB(int userId, String user, String password) {
        this.userId = userId;
        this.user = user;
        this.password = password;
    }

    public AccountDB() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
