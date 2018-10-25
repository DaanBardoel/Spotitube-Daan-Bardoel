package nl.han.oose.tracks;

public class TracksException extends RuntimeException {

    private String exceptionDescription;

    public TracksException(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }
}
