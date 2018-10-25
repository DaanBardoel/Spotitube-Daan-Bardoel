package nl.han.oose.entity;

public class PlaylistTrackDB {

    private int playlistID;
    private int trackID;

    public PlaylistTrackDB(int playlistID, int trackID) {
        this.playlistID = playlistID;
        this.trackID = trackID;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }
}
