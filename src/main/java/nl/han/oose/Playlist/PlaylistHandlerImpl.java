package nl.han.oose.Playlist;

import nl.han.oose.Login.Token;
import nl.han.oose.Persistence.PlaylistDAO;
import nl.han.oose.Persistence.TokenDAO;
import nl.han.oose.entity.PlaylistDB;
import nl.han.oose.entity.TokenDB;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistHandlerImpl implements PlaylistHandler {

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
        TokenDAO tdao = new TokenDAO();
        List<TokenDB> tokenDBs = tdao.getAllTokens();
        for (TokenDB tokenDB : tokenDBs) {
            if (tokenDB.getToken().equals(tokenstring)) {
                return new Token(tokenDB.getToken(), tokenDB.getuser());
            }
        }
        return null;
    }

    private List<Playlist> addDBPlaylistToPlaylistList(Token token) {
        List<Playlist> playlists = new ArrayList<>();
        PlaylistDAO pDAO = new PlaylistDAO();
        List<PlaylistDB> playlistDBs = pDAO.getAllPlaylists();
        for (PlaylistDB playlistDBIndex : playlistDBs) {
            if (token.getuser() == playlistDBIndex.getOwnerID()) {
                playlists.add(new Playlist(playlistDBIndex.getPlaylistID(), playlistDBIndex.getPlaylistname(), true, new String[]{}));
            } else {
                playlists.add(new Playlist(playlistDBIndex.getPlaylistID(), playlistDBIndex.getPlaylistname(), false, new String[]{}));
            }
        }
        return playlists;
    }
}
