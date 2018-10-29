package nl.han.oose.Persistence;

import nl.han.oose.entity.Track;
import nl.han.oose.tracks.TracksException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO implements ITrackDAO {

    private ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public int getTotalDuration() {
        int durationToReturn = 0;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT t.duration\n" +
                        "FROM Tracks t INNER JOIN Playlistcontent pc ON t.numberID = pc.trackID"
                );
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int durationFromDatabase = resultSet.getInt("duration");
                durationToReturn += durationFromDatabase;
            }
            return durationToReturn;
        } catch (SQLException e) {
            throw new TracksException("Oops, something went wrong in the database.");
        }
    }

    @Override
    public List<Track> getAllTracksExceptInCurrentPlaylist(int playlistID) {
        List<Track> tracks;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT numberID, title, performer, duration, album, playcount, publicationDate, description, CAST(0 AS bit) AS offlineAvailability FROM Tracks WHERE numberID NOT IN (SELECT trackID FROM Playlistcontent WHERE playlistID = (?))");
        ) {
            statement.setInt(1, playlistID);
            ResultSet resultSet = statement.executeQuery();
            tracks = resultsetMethod(resultSet);
        } catch (SQLException e) {
            throw new TracksException("Oops, something went wrong in the database.");
        }

        return tracks;
    }

    @Override
    public List<Track> getAllTracksForThisPlaylist(int playlistID) {
        List<Track> tracks;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT t.numberID, t.title, t.performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, pc.offlineAvailability FROM Tracks t INNER JOIN Playlistcontent pc ON T.numberID = pc.trackID WHERE pc.playlistID = (?)"
                );
        ) {
            statement.setInt(1, playlistID);
            ResultSet resultSet = statement.executeQuery();
            tracks = resultsetMethod(resultSet);
        } catch (SQLException e) {
            throw new TracksException("Oops, something went wrong in the database.");
        }

        return tracks;
    }

    private List<Track> resultsetMethod(ResultSet resultSet) throws SQLException {

        List<Track> tracks = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("numberID");
            String title = resultSet.getString("title");
            String performer = resultSet.getString("performer");
            int duration = resultSet.getInt("duration");
            String albumName = resultSet.getString("album");
            int playcount = resultSet.getInt("playcount");
            String publicationDate = resultSet.getString("publicationDate");
            String description = resultSet.getString("description");
            boolean offlineAvailability = resultSet.getBoolean("offlineAvailability");
            tracks.add(new Track(id, title, performer, duration, albumName, playcount, publicationDate, description, offlineAvailability));
        }
        return tracks;
    }
}