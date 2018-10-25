package nl.han.oose.tracks;

import nl.han.oose.Persistence.TrackDAO;
import nl.han.oose.entity.TrackDB;

import javax.inject.Inject;
import java.util.List;

public class TracksHandlerImpl implements TracksHandler {

    @Inject
    TrackDAO trackDAO;

    @Override
    public List<TrackDB> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token) {
        return trackDAO.getAllTracksExceptInPlaylistTracks();
    }
}
