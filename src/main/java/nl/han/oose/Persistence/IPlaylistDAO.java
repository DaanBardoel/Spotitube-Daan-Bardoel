package nl.han.oose.Persistence;

import nl.han.oose.entity.DTO.PlaylistDTO;
import nl.han.oose.entity.Playlist;
import nl.han.oose.entity.Token;

import java.util.List;

public interface IPlaylistDAO {

    List<PlaylistDTO> getAllPlaylists(Token token);

    void persistPlaylist(Playlist playlist);

    void deletePlaylist(int id);

    void editPlaylist(int id, PlaylistDTO playlistDTO);
}
