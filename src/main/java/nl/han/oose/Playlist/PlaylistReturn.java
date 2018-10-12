package nl.han.oose.Playlist;

import java.util.List;

public class PlaylistReturn {

    private int length = 5000;
    private List<Playlist> playlists;

    public PlaylistReturn(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
