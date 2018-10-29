package nl.han.oose.persistence;

import nl.han.oose.entity.Track;

public interface IPlaylistTrackDAO {

    void insertTrackIntoPlaylistTrack(int playlistID, Track trackID);

    void deleteRecordFromPlaylist(int playlistID, int trackID);
}
