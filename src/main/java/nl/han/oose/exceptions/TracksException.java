package nl.han.oose.exceptions;

public class TracksException extends RuntimeException {

    private String exceptionDescription;

    public TracksException(String exceptionDescription) {
        super(exceptionDescription);
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }
}
