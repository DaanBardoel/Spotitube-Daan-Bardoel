package nl.han.oose.Playlist;

import nl.han.oose.entity.TracksDB;
import nl.han.oose.token.Token;

import java.util.List;

public interface PlaylistHandler {

    List<Playlist> getPlayListStorage(String tokenString) throws PlaylistException;

    Token doesTokenExistInList(String tokenstring);

    int returnTotalLength();

    List<Playlist> addNewPlaylistAndReturnAllPlaylists(String tokenString, Playlist playlist);

    List<Playlist> deletePlaylistAndReturnAllPlaylists(String tokenString, int id);

    List<Playlist> editPlaylistAndReturnAllPlaylists(String tokenString, int id, Playlist playlist);

    List<TracksDB> getAllTracksForThisPlaylist(String tokenString, int id);
}
