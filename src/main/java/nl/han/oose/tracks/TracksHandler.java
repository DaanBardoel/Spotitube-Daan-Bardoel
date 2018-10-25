package nl.han.oose.tracks;

import nl.han.oose.entity.TracksDB;

import java.util.List;

public interface TracksHandler {
    List<TracksDB> getAllTracksExceptFromCurrentPlaylist(int playlistID, String token);
}
