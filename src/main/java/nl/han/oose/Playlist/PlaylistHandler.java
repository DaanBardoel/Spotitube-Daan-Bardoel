package nl.han.oose.Playlist;

import nl.han.oose.entity.Track;

import java.util.List;

public interface PlaylistHandler {

    List<Playlist> getPlayListStorage(String tokenString) throws PlaylistException;

    int returnTotalLength();

    List<Playlist> addNewPlaylistAndReturnAllPlaylists(String tokenString, Playlist playlist);

    List<Playlist> deletePlaylistAndReturnAllPlaylists(String tokenString, int id);

    List<Playlist> editPlaylistAndReturnAllPlaylists(String tokenString, int id, Playlist playlist);

    List<Track> getAllTracksForThisPlaylist(String tokenString, int id);

    void addTracksToGivenPlaylist(String tokenString, int id, Track track);

    void deleteGivenTrackFromPlaylist(String tokenString, int playlistID, int trackID);
}
