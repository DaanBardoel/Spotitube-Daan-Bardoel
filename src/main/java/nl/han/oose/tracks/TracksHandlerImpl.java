package nl.han.oose.tracks;

import nl.han.oose.Persistence.ITrackDAO;
import nl.han.oose.Playlist.PlaylistHandler;
import nl.han.oose.entity.Track;
import nl.han.oose.entity.TrackDB;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TracksHandlerImpl implements TracksHandler {

    @Inject
    ITrackDAO trackDAO;

    @Inject
    PlaylistHandler playlistHandler;

    @Override
    public List<Track> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token) {

        List<Track> resultTracks = new ArrayList<>();

        if (playlistHandler.doesTokenExistInList(token) != null) {

            List<TrackDB> tracksFromDatabase = trackDAO.getAllTracksExceptInPlaylistTracks(playlistID);

            for (TrackDB trackIndex : tracksFromDatabase) {
                resultTracks.add(new Track(trackIndex.getId(), trackIndex.getTitle(), trackIndex.getPerformer(), trackIndex.getDuration(), trackIndex.getAlbum(), trackIndex.getPlaycount(), trackIndex.getPublicationDate(), trackIndex.getDescription(), false));
            }
            return resultTracks;
        } else {
            throw new TracksException("The token does not exist. Please log in!");
        }
    }
}
