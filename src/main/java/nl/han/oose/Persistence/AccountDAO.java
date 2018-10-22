package nl.han.oose.Persistence;

import nl.han.oose.entity.AccountDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private ConnectionFactory connectionFactory;

    public AccountDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<AccountDB> getAllAccounts() {
        List<AccountDB> accountDBS = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOUNT");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("userID");
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                accountDBS.add(new AccountDB(userId, user, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountDBS;
    }
}
