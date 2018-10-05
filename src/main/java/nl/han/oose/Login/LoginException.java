package nl.han.oose.Login;

public class LoginException extends RuntimeException {

    private String exceptionname;

    public LoginException(String exceptionname) {
        this.exceptionname = exceptionname;
    }

    public String getExceptionname() {
        return exceptionname;
    }

    public void setExceptionname(String exceptionname) {
        this.exceptionname = exceptionname;
    }
}
