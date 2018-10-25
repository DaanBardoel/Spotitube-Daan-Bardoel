package nl.han.oose.Persistence;

import nl.han.oose.entity.TrackDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO implements ITracksDAO {

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


    public List<TrackDB> getAllTracksExceptInPlaylistTracks() {
        List<TrackDB> tracks;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks WHERE numberID NOT IN (SELECT trackID FROM Playlistcontent)");
        ) {
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
            boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
            tracks.add(new TrackDB(id, title, performer, duration, albumName, playcount, publicationDate, description, offlineAvailable));
        }
        return tracks;
    }
}