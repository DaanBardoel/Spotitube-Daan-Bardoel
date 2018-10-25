package nl.han.oose.tracks;

import nl.han.oose.Persistence.TracksDAO;
import nl.han.oose.entity.TracksDB;

import javax.inject.Inject;
import java.util.List;

public class TracksHandlerImpl implements TracksHandler {

    @Inject
    TracksDAO tracksDAO;

    @Override
    public List<TracksDB> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token) {
        return tracksDAO.getAllTracksExceptInPlaylistTracks();
    }
}
