package nl.han.oose.Playlist;

import nl.han.oose.Persistence.IPlaylistDAO;
import nl.han.oose.Persistence.IPlaylistTrackDAO;
import nl.han.oose.Persistence.ITokenDAO;
import nl.han.oose.Persistence.ITracksDAO;
import nl.han.oose.entity.PlaylistDB;
import nl.han.oose.entity.PlaylistTrackDB;
import nl.han.oose.entity.TokenDB;
import nl.han.oose.entity.TracksDB;
import nl.han.oose.token.Token;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistHandlerImpl implements PlaylistHandler {

    @Inject
    IPlaylistDAO playlistDAO;

    @Inject
    ITokenDAO tokenDAO;

    @Inject
    IPlaylistTrackDAO playlistTracksDAO;

    @Inject
    ITracksDAO tracksDAO;

    @Override
    public List<Playlist> getPlayListStorage(String tokenString) throws PlaylistException {
        Token token = doesTokenExistInList(tokenString);
        if (token != null) {
            return this.addDBPlaylistToPlaylistList(token);
        } else {
            throw new PlaylistException("Incorrect token!");
        }
    }

    @Override
    public Token doesTokenExistInList(String tokenstring) {
        List<TokenDB> tokenDBs = tokenDAO.getAllTokens();
        for (TokenDB tokenDB : tokenDBs) {
            if (tokenDB.getToken().equals(tokenstring)) {
                return new Token(tokenDB.getToken(), tokenDB.getuser());
            }
        }
        return null;
    }

    private List<Playlist> addDBPlaylistToPlaylistList(Token token) {
        List<Playlist> playlists = new ArrayList<>();
        List<PlaylistDB> playlistDBs = playlistDAO.getAllPlaylists();
        for (PlaylistDB playlistDBIndex : playlistDBs) {
            if (token.getUserID() == playlistDBIndex.getOwnerID()) {
                playlists.add(new Playlist(playlistDBIndex.getPlaylistID(), playlistDBIndex.getPlaylistname(), true, new String[]{}));
            } else {
                playlists.add(new Playlist(playlistDBIndex.getPlaylistID(), playlistDBIndex.getPlaylistname(), false, new String[]{}));
            }
        }
        return playlists;
    }

    @Override
    public int returnTotalLength() {
        List<PlaylistTrackDB> playlistTrackDBs = playlistTracksDAO.getAllPlaylistTracks();
        List<TracksDB> tracksDB = tracksDAO.getAllTracks();
        int duration = 0;
        for (PlaylistTrackDB playlistTrackDBsIndex : playlistTrackDBs) {
            for (TracksDB tracksDBIndex : tracksDB) {
                if (tracksDBIndex.getNumberID() == playlistTrackDBsIndex.getTrackID()) {
                    duration += tracksDBIndex.getDuration();
                    break;
                }
            }
        }
        return duration;
    }

    @Override
    public List<Playlist> addNewPlaylistAndReturnAllPlaylists(String tokenString, Playlist playlist) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            int defaultPlaylistIDPlaceholder = -1;
            PlaylistDB playlistDB = new PlaylistDB(defaultPlaylistIDPlaceholder, playlist.getName(), token.getUserID());
            playlistDAO.persistPlaylist(playlistDB);
            return this.addDBPlaylistToPlaylistList(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public List<Playlist> deletePlaylistAndReturnAllPlaylists(String tokenString, int id) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            playlistDAO.deletePlaylist(id);
            return this.addDBPlaylistToPlaylistList(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }

    @Override
    public List<Playlist> editPlaylistAndReturnAllPlaylists(String tokenString, int id, Playlist playlist) {

        Token token = this.doesTokenExistInList(tokenString);

        if (token != null) {
            playlistDAO.editPlaylist(id, playlist);
            return this.addDBPlaylistToPlaylistList(token);
        } else {
            throw new PlaylistException("The token does not exist. Please log in!");
        }
    }
}
