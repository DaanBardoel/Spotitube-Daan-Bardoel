package nl.han.oose.entity;

public class PlaylistDB {

    private int playlistID;
    private String playlistname;
    private int ownerID;

    public PlaylistDB(int playlistID, String playlistname, int ownerID) {
        this.playlistID = playlistID;
        this.playlistname = playlistname;
        this.ownerID = ownerID;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public void setPlaylistname(String playlistname) {
        this.playlistname = playlistname;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
