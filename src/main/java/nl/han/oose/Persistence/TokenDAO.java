package nl.han.oose.Persistence;

import nl.han.oose.exceptions.LoginException;
import nl.han.oose.entity.Token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TokenDAO implements ITokenDAO {

    private ConnectionFactory connectionFactory;

    public TokenDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Token> getAllTokens() {
        List<Token> tokens = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Token");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int user = resultSet.getInt("userID");
                String token = resultSet.getString("token");
                String dateString = resultSet.getString("validUntil");
                tokens.add(new Token(token, user, dateString));
            }
        } catch (SQLException e) {
            throw new LoginException("Oops, something went wrong in the database.");

        }
        return tokens;
    }

    @Override
    public void persistToken(Token token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT  INTO Token (userID, token, validUntil) VALUES (?,?,?)");
        ) {
            statement.setInt(1, token.getUserID());
            statement.setString(2, token.getToken());
            statement.setString(3, token.getDateString());
            statement.execute();

        } catch (SQLException e) {
            throw new LoginException("Oops, something went wrong in the database.");
        }
    }

    @Override
    public void deleteToken(Token token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM Token WHERE userID = (?)");
        ) {
            statement.setInt(1, token.getUserID());
            statement.execute();
        } catch (SQLException e) {
            throw new LoginException("Oops, something went wrong in the database.");
        }
    }

    @Override
    public Token getTokenForUserId(int userID) {

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM Token WHERE userID = (?)");
        ) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            boolean val = resultSet.next();

            return getTokenDB(resultSet, val);
        } catch (SQLException e) {
            throw new LoginException("Oops, something went wrong in the database.");
        }
    }

    @Override
    public Token getTokenForGivenTokenString(String tokenString) {

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM Token WHERE token = (?)");
        ) {
            statement.setString(1, tokenString);
            ResultSet resultSet = statement.executeQuery();

            boolean val = resultSet.next();

            return getTokenDB(resultSet, val);
        } catch (SQLException e) {
            throw new LoginException("Oops, something went wrong in the database.");
        }
    }

    private Token getTokenDB(ResultSet resultSet, boolean val) throws SQLException {
        if (!val) {
            return null;
        } else {
            int userIDfromDB = resultSet.getInt("userID");
            String tokenString = resultSet.getString("token");
            String dateString = resultSet.getString("validUntil");
            return new Token(tokenString, userIDfromDB, dateString);

        }
    }
}
