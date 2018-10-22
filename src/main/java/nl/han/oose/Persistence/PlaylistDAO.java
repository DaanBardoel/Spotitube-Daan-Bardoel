package nl.han.oose.Persistence;

import nl.han.oose.entity.PlaylistDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<PlaylistDB> getAllPlaylists() {
        List<PlaylistDB> playlistDBs = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Playlists")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int playlistID = resultSet.getInt("playlistID");
                String playlistname = resultSet.getString("playlistname");
                int ownerID = resultSet.getInt("ownerID");
                playlistDBs.add(new PlaylistDB(playlistID, playlistname, ownerID));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistDBs;
    }
}
