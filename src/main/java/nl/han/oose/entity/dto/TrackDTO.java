package nl.han.oose.entity.dto;

import nl.han.oose.entity.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackDTO {

    private List<Track> tracks = new ArrayList<>();

    public TrackDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
