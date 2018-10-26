package nl.han.oose.tracks;

import nl.han.oose.entity.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackReturn {

    private List<Track> tracks = new ArrayList<>();

    public TrackReturn(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
