package nl.han.oose.tracks;

import nl.han.oose.entity.TrackDB;

import java.util.List;

public class TrackReturn {

    private List<TrackDB> tracks;

    public TrackReturn(List<TrackDB> tracks) {
        this.tracks = tracks;
    }

    public List<TrackDB> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDB> tracks) {
        this.tracks = tracks;
    }
}
