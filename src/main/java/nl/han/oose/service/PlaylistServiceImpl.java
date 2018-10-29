package nl.han.oose.service;

import nl.han.oose.entity.Playlist;
import nl.han.oose.entity.Token;
import nl.han.oose.entity.Track;
import nl.han.oose.entity.dto.PlaylistDTO;
import nl.han.oose.exceptions.PlaylistException;
import nl.han.oose.persistence.IPlaylistDAO;
import nl.han.oose.persistence.IPlaylistTrackDAO;
import nl.han.oose.persistence.ITokenDAO;
import nl.han.oose.persistence.ITrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    @Inject
    IPlaylistDAO playlistDAO;

    @Inject
    ITokenDAO tokenDAO;

    @Inject
    IPlaylistTrackDAO playlistTracksDAO;

    @Inject
    ITrackDAO tracksDAO;

    @Override
    public List<PlaylistDTO> getPlayListStorage(String tokenString) throws PlaylistException {
        Token token = doesTokenExistInList(tokenString);
        if (token != null) {
            return playlistDAO.getAllPlaylists(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public int returnTotalLength() {
        return tracksDAO.getTotalDuration();
    }

    @Override
    public List<PlaylistDTO> addNewPlaylistAndReturnAllPlaylists(String tokenString, PlaylistDTO playlistDTO) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            int defaultPlaylistIDPlaceholder = -1;
            Playlist playlist = new Playlist(defaultPlaylistIDPlaceholder, playlistDTO.getName(), token.getUserID());
            playlistDAO.persistPlaylist(playlist);
            return playlistDAO.getAllPlaylists(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public List<PlaylistDTO> deletePlaylistAndReturnAllPlaylists(String tokenString, int id) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            playlistDAO.deletePlaylist(id);
            return playlistDAO.getAllPlaylists(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public List<PlaylistDTO> editPlaylistAndReturnAllPlaylists(String tokenString, int id, PlaylistDTO playlistDTO) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            playlistDAO.editPlaylist(id, playlistDTO);
            return playlistDAO.getAllPlaylists(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public List<Track> getAllTracksForThisPlaylist(String tokenString, int id) {
        return tracksDAO.getAllTracksForThisPlaylist(id);
    }

    @Override
    public void addTracksToGivenPlaylist(String tokenString, int id, Track track) {
        if (this.doesTokenExistInList(tokenString) != null) {
            playlistTracksDAO.insertTrackIntoPlaylistTrack(id, track);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public void deleteGivenTrackFromPlaylist(String tokenString, int playlistID, int trackID) {
        if (this.doesTokenExistInList(tokenString) != null) {
            playlistTracksDAO.deleteRecordFromPlaylist(playlistID, trackID);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public Token doesTokenExistInList(String tokenstring) {
        Token token = tokenDAO.getTokenForGivenTokenString(tokenstring);
        if (token == null) {
            return null;
        } else {
            return token;
        }
    }
}
