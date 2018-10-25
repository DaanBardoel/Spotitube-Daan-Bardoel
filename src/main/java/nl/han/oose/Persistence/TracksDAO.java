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
        List<TracksDB> tracksDB = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks");
        ) {
            ResultSet resultSet = statement.executeQuery();
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
                tracksDB.add(new TracksDB(numberID, title, performer, duration, albumName, playcount, publicationDate, description, offlineAvailable));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracksDB;
    }
}
