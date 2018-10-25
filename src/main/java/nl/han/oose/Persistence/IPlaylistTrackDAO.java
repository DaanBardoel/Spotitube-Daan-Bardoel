package nl.han.oose.Persistence;

import nl.han.oose.entity.PlaylistTrackDB;

import java.util.List;

public interface IPlaylistTrackDAO {
    List<PlaylistTrackDB> getAllPlaylistTracks();

    void insertTrackIntoPlaylistTrack(int playlistID, int trackID);
}
