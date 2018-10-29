package nl.han.oose.Playlist;

import nl.han.oose.Persistence.IPlaylistDAO;
import nl.han.oose.Persistence.IPlaylistTrackDAO;
import nl.han.oose.Persistence.ITokenDAO;
import nl.han.oose.Persistence.ITrackDAO;
import nl.han.oose.entity.PlaylistDB;
import nl.han.oose.entity.TokenDB;
import nl.han.oose.entity.Track;
import nl.han.oose.token.Token;

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
    public List<Playlist> getPlayListStorage(String tokenString) throws PlaylistException {
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
    public List<Playlist> addNewPlaylistAndReturnAllPlaylists(String tokenString, Playlist playlist) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            int defaultPlaylistIDPlaceholder = -1;
            PlaylistDB playlistDB = new PlaylistDB(defaultPlaylistIDPlaceholder, playlist.getName(), token.getUserID());
            playlistDAO.persistPlaylist(playlistDB);
            return playlistDAO.getAllPlaylists(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public List<Playlist> deletePlaylistAndReturnAllPlaylists(String tokenString, int id) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            playlistDAO.deletePlaylist(id);
            return playlistDAO.getAllPlaylists(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public List<Playlist> editPlaylistAndReturnAllPlaylists(String tokenString, int id, Playlist playlist) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            playlistDAO.editPlaylist(id, playlist);
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
        TokenDB tokenDB = tokenDAO.getTokenForGivenTokenString(tokenstring);
        if (tokenDB == null) {
            return null;
        } else {
            return new Token(tokenDB.getToken(), tokenDB.getuser());
        }
    }
}
