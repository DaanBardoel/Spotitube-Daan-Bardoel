package nl.han.oose.tracks;

import nl.han.oose.entity.Track;

import java.util.List;

public interface TracksHandler {
    List<Track> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token);
}
