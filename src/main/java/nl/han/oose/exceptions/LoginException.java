package nl.han.oose.exceptions;

public class LoginException extends RuntimeException {

    private String exceptionname;

    public LoginException(String exceptionname) {
        super(exceptionname);
    }

    public String getExceptionname() {
        return exceptionname;
    }

    public void setExceptionname(String exceptionname) {
        this.exceptionname = exceptionname;
    }
}
