package nl.han.oose.Playlist;

import java.util.List;

public interface PlaylistHandler {

    List<Playlist> getPlayListStorage(String tokenString) throws PlaylistException;

    boolean doesTokenExistInList(String tokenstring);
}
