package nl.han.oose.Persistence;

import nl.han.oose.entity.TracksDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TracksDAO implements ITracksDAO {

    private ConnectionFactory connectionFactory;

    public TracksDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<TracksDB> getAllTracks() {
        List<TracksDB> tracks;
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


    public List<TracksDB> getAllTracksExceptInPlaylistTracks() {
        List<TracksDB> tracks;
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

    private List<TracksDB> resultsetMethod(ResultSet resultSet) throws SQLException {

        List<TracksDB> tracks = new ArrayList<>();

        while (resultSet.next()) {
            int numberID = resultSet.getInt("numberID");
            String title = resultSet.getString("title");
            String performer = resultSet.getString("performer");
            int duration = resultSet.getInt("duration");
            String albumName = resultSet.getString("album");
            int playcount = resultSet.getInt("playcount");
            String publicationDate = resultSet.getString("publicationDate");
            String description = resultSet.getString("description");
            boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
            tracks.add(new TracksDB(numberID, title, performer, duration, albumName, playcount, publicationDate, description, offlineAvailable));
        }
        return tracks;
    }
}