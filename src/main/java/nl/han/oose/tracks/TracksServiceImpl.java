package nl.han.oose.tracks;

import nl.han.oose.Persistence.ITrackDAO;
import nl.han.oose.Playlist.PlaylistService;
import nl.han.oose.entity.Track;

import javax.inject.Inject;
import java.util.List;

public class TracksServiceImpl implements TracksService {

    @Inject
    ITrackDAO trackDAO;

    @Inject
    PlaylistService playlistService;

    @Override
    public List<Track> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token) {
        if (playlistService.doesTokenExistInList(token) != null) {
            return trackDAO.getAllTracksExceptInCurrentPlaylist(playlistID);
        } else {
            throw new TracksException("The token does not exist. Please log in!");
        }
    }
}
