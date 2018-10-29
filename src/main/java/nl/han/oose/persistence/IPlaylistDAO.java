package nl.han.oose.persistence;

import nl.han.oose.entity.Playlist;
import nl.han.oose.entity.Token;
import nl.han.oose.entity.dto.PlaylistDTO;

import java.util.List;

public interface IPlaylistDAO {

    List<PlaylistDTO> getAllPlaylists(Token token);

    void persistPlaylist(Playlist playlist);

    void deletePlaylist(int id);

    void editPlaylist(int id, PlaylistDTO playlistDTO);
}
