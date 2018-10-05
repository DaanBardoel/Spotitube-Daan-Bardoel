package nl.han.oose.Playlist;

public class PlaylistException extends RuntimeException {

    private String PlaylistExceptionDescription;

    public PlaylistException(String PlaylistExceptionDescription) {
        this.PlaylistExceptionDescription = PlaylistExceptionDescription;
    }

    public String getPlaylistExceptionDescription() {
        return PlaylistExceptionDescription;
    }

    public void setPlaylistExceptionDescription(String playlistExceptionDescription) {
        PlaylistExceptionDescription = playlistExceptionDescription;
    }


}
