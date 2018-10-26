package nl.han.oose.Persistence;

import nl.han.oose.entity.TrackDB;

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
    public List<TrackDB> getAllTracks() {
        List<TrackDB> tracks;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks");
        ) {
            ResultSet resultSet = statement.executeQuery();
            tracks = resultsetMethod(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }

    @Override
    public List<TrackDB> getAllTracksExceptInPlaylistTracks(int playlistID) {
        List<TrackDB> tracks;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks WHERE numberID NOT IN (SELECT trackID FROM Playlistcontent WHERE playlistID = (?))");
        ) {
            statement.setInt(1, playlistID);
            ResultSet resultSet = statement.executeQuery();
            tracks = resultsetMethod(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracks;
    }

    @Override
    public List<TrackDB> getAllTracksForThisPlaylist(int playlistID) {
        List<TrackDB> tracks;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks WHERE numberID IN (SELECT trackID FROM Playlistcontent WHERE playlistID = (?))");
        ) {
            statement.setInt(1, playlistID);
            ResultSet resultSet = statement.executeQuery();
            tracks = resultsetMethod(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracks;
    }

    private List<TrackDB> resultsetMethod(ResultSet resultSet) throws SQLException {

        List<TrackDB> tracks = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("numberID");
            String title = resultSet.getString("title");
            String performer = resultSet.getString("performer");
            int duration = resultSet.getInt("duration");
            String albumName = resultSet.getString("album");
            int playcount = resultSet.getInt("playcount");
            String publicationDate = resultSet.getString("publicationDate");
            String description = resultSet.getString("description");
            tracks.add(new TrackDB(id, title, performer, duration, albumName, playcount, publicationDate, description));
        }
        return tracks;
    }
}