package nl.han.oose.tracks;

import nl.han.oose.entity.TrackDB;

import java.util.List;

public interface TracksHandler {
    List<TrackDB> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token);
}
