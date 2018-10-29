package nl.han.oose.Persistence;

import nl.han.oose.Playlist.Playlist;
import nl.han.oose.Playlist.PlaylistException;
import nl.han.oose.entity.PlaylistDB;
import nl.han.oose.token.Token;

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
    public List<Playlist> getAllPlaylists(Token token) {
        List<Playlist> playlists = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Playlists")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int playlistID = resultSet.getInt("playlistID");
                String playlistname = resultSet.getString("playlistname");
                int ownerID = resultSet.getInt("ownerID");
                if (ownerID == token.getUserID()) {
                    playlists.add(new Playlist(playlistID, playlistname, true, new String[]{}));
                } else {
                    playlists.add(new Playlist(playlistID, playlistname, false, new String[]{}));
                }
            }
        } catch (SQLException e) {
            throw new PlaylistException("Oops. something went wrong in the database.");
        }
        return playlists;
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
            throw new PlaylistException("Oops. something went wrong in the database.");
        }
    }

    @Override
    public void deletePlaylist(int id) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM Playlists WHERE playlistID = (?)"
                );
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new PlaylistException("Oops. something went wrong in the database.");
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
            throw new PlaylistException("Oops. something went wrong in the database.");
        }
    }
}
