package nl.han.oose.Persistence;

import nl.han.oose.entity.TokenDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TokenDAO {

    private ConnectionFactory connectionFactory;

    public TokenDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<TokenDB> getAllTokens() {
        List<TokenDB> tokenDBs = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Token");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int user = resultSet.getInt("userID");
                String token = resultSet.getString("token");
                String dateString = resultSet.getString("validUntil");
                tokenDBs.add(new TokenDB(token, user, dateString));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return tokenDBs;
    }

    public void persistToken(TokenDB tokenDB) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT  INTO Token (userID, token, validUntil) VALUES (?,?,?)");
        ) {
            statement.setInt(1, tokenDB.getuser());
            statement.setString(2, tokenDB.getToken());
            statement.setString(3, tokenDB.getDateString());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteToken(TokenDB tokenDB) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM Token WHERE userID = (?)");
        ) {
            statement.setInt(1, tokenDB.getuser());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
