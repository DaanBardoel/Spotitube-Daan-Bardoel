package nl.han.oose.persistence;

import nl.han.oose.entity.Playlist;
import nl.han.oose.entity.Token;
import nl.han.oose.entity.dto.PlaylistDTO;
import nl.han.oose.exceptions.PlaylistException;

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
    public List<PlaylistDTO> getAllPlaylists(Token token) {
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();
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
                    playlistDTOS.add(new PlaylistDTO(playlistID, playlistname, true, new String[]{}));
                } else {
                    playlistDTOS.add(new PlaylistDTO(playlistID, playlistname, false, new String[]{}));
                }
            }
        } catch (SQLException e) {
            throw new PlaylistException("Oops. something went wrong in the database.");
        }
        return playlistDTOS;
    }

    @Override
    public void persistPlaylist(Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT  INTO Playlists (playlistname, ownerID) VALUES (?,?)");
        ) {
            statement.setString(1, playlist.getPlaylistname());
            statement.setInt(2, playlist.getOwnerID());
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
    public void editPlaylist(int id, PlaylistDTO playlistDTO) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Playlists SET playlistname = (?) WHERE playlistID = (?)"
                );
        ) {
            statement.setString(1, playlistDTO.getName());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new PlaylistException("Oops. something went wrong in the database.");
        }
    }
}
