package nl.han.oose.Persistence;

import nl.han.oose.Playlist.Playlist;
import nl.han.oose.entity.PlaylistDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO implements IPlaylistDAO {

    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
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

    @Override
    public void persistPlaylist(PlaylistDB playlistDB) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT  INTO Playlists (playlistname, ownerID) VALUES (?,?)");
        ) {
            statement.setString(1, playlistDB.getPlaylistname());
            statement.setInt(2, playlistDB.getOwnerID());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePlaylist(int id) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM Playlists WHERE playlistID = (?)"
                );
                PreparedStatement statement1 = connection.prepareStatement(
                        "DELETE * FROM Playlistcontent WHERE playlistID = (?)"
                );
        ) {
            statement.setInt(1, id);
            statement.execute();
            statement1.setInt(1, id);
            statement1.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editPlaylist(int id, Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Playlists SET playlistname = (?) WHERE playlistID = (?)"
                );
        ) {
            statement.setString(1, playlist.getName());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
