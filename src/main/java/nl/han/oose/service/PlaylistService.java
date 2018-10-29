package nl.han.oose.service;

import nl.han.oose.entity.Token;
import nl.han.oose.entity.Track;
import nl.han.oose.entity.dto.PlaylistDTO;
import nl.han.oose.exceptions.PlaylistException;

import java.util.List;

public interface PlaylistService {

    List<PlaylistDTO> getPlayListStorage(String tokenString) throws PlaylistException;

    int returnTotalLength();

    List<PlaylistDTO> addNewPlaylistAndReturnAllPlaylists(String tokenString, PlaylistDTO playlistDTO);

    List<PlaylistDTO> deletePlaylistAndReturnAllPlaylists(String tokenString, int id);

    List<PlaylistDTO> editPlaylistAndReturnAllPlaylists(String tokenString, int id, PlaylistDTO playlistDTO);

    List<Track> getAllTracksForThisPlaylist(String tokenString, int id);

    void addTracksToGivenPlaylist(String tokenString, int id, Track track);

    void deleteGivenTrackFromPlaylist(String tokenString, int playlistID, int trackID);

    Token doesTokenExistInList(String tokenstring);
}
