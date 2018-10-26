package nl.han.oose.Persistence;

import nl.han.oose.entity.TrackDB;

import java.util.List;

public interface ITrackDAO {
    List<TrackDB> getAllTracks();

    List<TrackDB> getAllTracksExceptInPlaylistTracks(int playlistID);

    List<TrackDB> getAllTracksForThisPlaylist(int playlistID);
}
