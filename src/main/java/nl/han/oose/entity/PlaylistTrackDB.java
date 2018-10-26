package nl.han.oose.entity;

public class PlaylistTrackDB {

    private int playlistID;
    private int trackID;
    private boolean offlineAvailability;

    public PlaylistTrackDB(int playlistID, int trackID, boolean offlineAvailability) {
        this.playlistID = playlistID;
        this.trackID = trackID;
        this.offlineAvailability = offlineAvailability;
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

    public boolean isOfflineAvailability() {
        return offlineAvailability;
    }

    public void setOfflineAvailability(boolean offlineAvailability) {
        this.offlineAvailability = offlineAvailability;
    }
}
