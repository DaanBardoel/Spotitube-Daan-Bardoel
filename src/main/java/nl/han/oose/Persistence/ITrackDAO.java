package nl.han.oose.Persistence;

import nl.han.oose.entity.Track;

import java.util.List;

public interface ITrackDAO {

    int getTotalDuration();

    List<Track> getAllTracksExceptInCurrentPlaylist(int playlistID);

    List<Track> getAllTracksForThisPlaylist(int playlistID);
}
