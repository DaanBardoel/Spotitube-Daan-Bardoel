package nl.han.oose.persistence;

import nl.han.oose.entity.Track;
import nl.han.oose.exceptions.PlaylistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistTrackDAO implements IPlaylistTrackDAO {

    private ConnectionFactory connectionFactory;

    public PlaylistTrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public void insertTrackIntoPlaylistTrack(int playlistID, Track track) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Playlistcontent(playlistID, trackID, offlineAvailability) VALUES (?,?,?)");
        ) {
            statement.setInt(1, playlistID);
            statement.setInt(2, track.getId());
            statement.setBoolean(3, track.isOfflineAvailable());
            statement.execute();
        } catch (SQLException e) {
            throw new PlaylistException("Oops. something went wrong in the database.");
        }
    }

    @Override
    public void deleteRecordFromPlaylist(int playlistID, int trackID) {

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Playlistcontent WHERE playlistID = (?) AND trackID = (?)");
        ) {
            statement.setInt(1, playlistID);
            statement.setInt(2, trackID);
            statement.execute();
        } catch (SQLException e) {
            throw new PlaylistException("Oops. something went wrong in the database.");
        }
    }
}
