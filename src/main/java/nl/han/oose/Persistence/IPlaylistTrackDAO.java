package nl.han.oose.Persistence;

import nl.han.oose.entity.PlaylistTrackDB;
import nl.han.oose.entity.Track;

import java.util.List;

public interface IPlaylistTrackDAO {
    List<PlaylistTrackDB> getAllPlaylistTracks();

    void insertTrackIntoPlaylistTrack(int playlistID, Track trackID);

    List<PlaylistTrackDB> getAllPlaylistTracksForThisPlaylist(int playlistID);

    void deleteRecordFromPlaylist(int playlistID, int trackID);
}
