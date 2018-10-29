package nl.han.oose.Playlist;

public class PlaylistException extends RuntimeException {

    private String playlistExceptionDescription;

    public PlaylistException(String playlistExceptionDescription) {
        super(playlistExceptionDescription);
    }

    public String getPlaylistExceptionDescription() {
        return playlistExceptionDescription;
    }

    public void setPlaylistExceptionDescription(String playlistExceptionDescription) {
        this.playlistExceptionDescription = playlistExceptionDescription;
    }


}
