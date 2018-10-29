package nl.han.oose.Persistence;

import nl.han.oose.Playlist.Playlist;
import nl.han.oose.entity.PlaylistDB;
import nl.han.oose.token.Token;

import java.util.List;

public interface IPlaylistDAO {
    List<Playlist> getAllPlaylists(Token token);

    void persistPlaylist(PlaylistDB playlistDB);

    void deletePlaylist(int id);

    void editPlaylist(int id, Playlist playlist);
}
