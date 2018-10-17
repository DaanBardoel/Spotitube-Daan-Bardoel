package nl.han.oose.Persistence;

import nl.han.oose.entity.Token;

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

    public List<Token> getAllTokens() {
        List<Token> tokens = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Token");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String token = resultSet.getString("token");
                tokens.add(new Token(token, user));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return tokens;
    }

    public void persistToken(Token token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT  INTO Token ([user], token) VALUES (?,?)");
        ) {
            statement.setString(1, token.getuser());
            statement.setString(2, token.getToken());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
