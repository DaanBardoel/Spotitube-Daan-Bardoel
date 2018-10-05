package nl.han.oose.Playlist;

import nl.han.oose.LocalStorage;
import nl.han.oose.Token;

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
            throw new PlaylistException();
        }
    }

    @Override
    public boolean doesTokenExistInList(String tokenstring) {
        List<Token> tokens = LocalStorage.getTokens();
        for (Token token : tokens) {
            if (token.getToken().equals(tokenstring)) {
                return true;
            }
        }
        return false;
    }
}
