package nl.han.oose.Persistence;

import nl.han.oose.Playlist.Playlist;
import nl.han.oose.entity.PlaylistDB;

import java.util.List;

public interface IPlaylistDAO {
    List<PlaylistDB> getAllPlaylists();

    void persistPlaylist(PlaylistDB playlistDB);

    void deletePlaylist(int id);

    void editPlaylist(int id, Playlist playlist);
}
