package nl.han.oose;

public class LoginException extends Exception {

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
