package nl.han.oose.Persistence;

import nl.han.oose.entity.Account;

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

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOUNT");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("userID");
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                accounts.add(new Account(userId, user, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }
}
