package nl.han.oose.tracks;

import nl.han.oose.entity.Track;

import java.util.List;

public interface TracksService {
    List<Track> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token);
}
