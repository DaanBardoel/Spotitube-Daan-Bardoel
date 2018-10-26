package nl.han.oose.tracks;

import nl.han.oose.Persistence.ITrackDAO;
import nl.han.oose.Playlist.PlaylistHandler;
import nl.han.oose.entity.Track;

import javax.inject.Inject;
import java.util.List;

public class TracksHandlerImpl implements TracksHandler {

    @Inject
    ITrackDAO trackDAO;

    @Inject
    PlaylistHandler playlistHandler;

    @Override
    public List<Track> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token) {
        if (playlistHandler.doesTokenExistInList(token) != null) {
            return trackDAO.getAllTracksExceptInPlaylistTracks(playlistID);
        } else {
            throw new TracksException("The token does not exist. Please log in!");
        }
    }
}
