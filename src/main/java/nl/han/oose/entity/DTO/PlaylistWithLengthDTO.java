package nl.han.oose.entity.DTO;

import java.util.List;

public class PlaylistWithLengthDTO {

    private List<PlaylistDTO> playlists;
    private int length;

    public PlaylistWithLengthDTO(List<PlaylistDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }
}
