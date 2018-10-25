package nl.han.oose.tracks;

import nl.han.oose.Persistence.PlaylistTrackDAO;
import nl.han.oose.Persistence.TracksDAO;
import nl.han.oose.entity.PlaylistTrackDB;
import nl.han.oose.entity.TracksDB;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TracksHandlerImpl implements TracksHandler {

    @Inject
    TracksDAO tracksDAO;

    @Inject
    PlaylistTrackDAO playlistTrackDAO;

    @Override
    public List<TracksDB> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token) {
        List<TracksDB> tracks = tracksDAO.getAllTracks();

        List<PlaylistTrackDB> playlistTracks = playlistTrackDAO.getAllPlaylistTracks();

        List<TracksDB> resultTracks = new ArrayList<>();

        for (TracksDB trackIndex : tracks) {
            for (PlaylistTrackDB playlistIndex : playlistTracks) {
                if (trackIndex.getNumberID() == playlistIndex.getTrackID()) {
                    break;
                }
            }
            resultTracks.add(trackIndex);
        }
        return resultTracks;
    }
}
