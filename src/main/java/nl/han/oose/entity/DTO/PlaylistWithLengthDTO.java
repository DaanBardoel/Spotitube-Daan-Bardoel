package nl.han.oose.entity.DTO;

import java.util.List;

public class PlaylistWithLengthDTO {

    private List<PlaylistDTO> playlistDTOS;
    private int length;

    public PlaylistWithLengthDTO(List<PlaylistDTO> playlistDTOS, int length) {
        this.playlistDTOS = playlistDTOS;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<PlaylistDTO> getPlaylistDTOS() {
        return playlistDTOS;
    }

    public void setPlaylistDTOS(List<PlaylistDTO> playlistDTOS) {
        this.playlistDTOS = playlistDTOS;
    }
}
