package nl.han.oose.Playlist;

import nl.han.oose.Persistence.TokenDAO;
import nl.han.oose.entity.TokenDB;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistHandlerImpl implements PlaylistHandler {

    @Override
    public List<Playlist> getPlayListStorage(String tokenString) throws PlaylistException {
        if (doesTokenExistInList(tokenString)) {
            Playlist playlist = new Playlist(1, "Death metal", true, new String[]{});
            Playlist playlist1 = new Playlist(2, "pop", false, new String[]{});
            List<Playlist> playlists = new ArrayList<>();
            playlists.add(playlist);
            playlists.add(playlist1);
            return playlists;
        } else {
            throw new PlaylistException("Incorrect token!");
        }
    }

    @Override
    public boolean doesTokenExistInList(String tokenstring) {
        TokenDAO tdao = new TokenDAO();
        List<TokenDB> tokenDBs = tdao.getAllTokens();
        for (TokenDB tokenDB : tokenDBs) {
            if (tokenDB.getToken().equals(tokenstring)) {
                return true;
            }
        }
        return false;
    }
}
