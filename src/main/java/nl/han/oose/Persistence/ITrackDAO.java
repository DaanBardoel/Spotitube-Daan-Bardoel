package nl.han.oose.Persistence;

import nl.han.oose.entity.Track;

import java.util.List;

public interface ITrackDAO {
    List<Track> getAllTracks();

    List<Track> getAllTracksExceptInPlaylistTracks(int playlistID);
}
