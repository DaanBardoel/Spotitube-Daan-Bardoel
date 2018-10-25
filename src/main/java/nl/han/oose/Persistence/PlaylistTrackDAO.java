package nl.han.oose.Persistence;

import nl.han.oose.entity.PlaylistTrackDB;

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
        List<PlaylistTrackDB> playlistTrackDBs = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Playlistcontent");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int playlistID = resultSet.getInt("playlistID");
                int trackID = resultSet.getInt("trackID");
                playlistTrackDBs.add(new PlaylistTrackDB(playlistID, trackID));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistTrackDBs;
    }

    @Override
    public void insertTrackIntoPlaylistTrack(int playlistID, int trackID) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Playlistcontent(playlistID, trackID) VALUES (?,?)");
        ) {
            statement.setInt(1, playlistID);
            statement.setInt(2, trackID);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
