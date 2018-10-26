package nl.han.oose.Persistence;

import nl.han.oose.entity.PlaylistTrackDB;
import nl.han.oose.entity.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistTrackDAO implements IPlaylistTrackDAO {

    private ConnectionFactory connectionFactory;

    public PlaylistTrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<PlaylistTrackDB> getAllPlaylistTracks() {
        List<PlaylistTrackDB> playlistTrackDBs;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Playlistcontent");
        ) {
            ResultSet resultSet = statement.executeQuery();
            playlistTrackDBs = resultSetMethod(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistTrackDBs;
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlaylistTrackDB> getAllPlaylistTracksForThisPlaylist(int playlistIDgiven) {
        List<PlaylistTrackDB> playlistTrackDBs;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Playlistcontent WHERE playlistID = (?)");
        ) {
            statement.setInt(1, playlistIDgiven);
            ResultSet resultSet = statement.executeQuery();
            playlistTrackDBs = this.resultSetMethod(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistTrackDBs;
    }

    private List<PlaylistTrackDB> resultSetMethod(ResultSet resultSet) throws SQLException {

        List<PlaylistTrackDB> playlistTrackDBs = new ArrayList<>();

        while (resultSet.next()) {
            int playlistID = resultSet.getInt("playlistID");
            int trackID = resultSet.getInt("trackID");
            boolean offlineAvailability = resultSet.getBoolean("offlineAvailability");
            playlistTrackDBs.add(new PlaylistTrackDB(playlistID, trackID, offlineAvailability));
        }
        return playlistTrackDBs;
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
            throw new RuntimeException(e);
        }
    }
}
