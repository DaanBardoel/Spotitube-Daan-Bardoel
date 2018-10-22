package nl.han.oose.Playlist;

import nl.han.oose.Login.Token;

import java.util.List;

public interface PlaylistHandler {

    List<Playlist> getPlayListStorage(String tokenString) throws PlaylistException;

    Token doesTokenExistInList(String tokenstring);
}
