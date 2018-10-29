package nl.han.oose.persistence;

import nl.han.oose.entity.Account;
import nl.han.oose.entity.dto.AccountDTO;
import nl.han.oose.exceptions.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccountDAO {

    private ConnectionFactory connectionFactory;

    public AccountDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
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
            throw new LoginException("Oops, something went wrong in the database.");
        }
        return accounts;
    }

    @Override
    public Account getAccountForGivenCredentials(AccountDTO credentials) {
        Account account;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Account WHERE [user] = (?) AND password = (?)");
        ) {
            statement.setString(1, credentials.getUser());
            statement.setString(2, credentials.getPassword());
            ResultSet resultSet = statement.executeQuery();

            boolean val = resultSet.next();

            if (!val) {
                return null;
            } else {
                int userID = resultSet.getInt("userID");
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                account = new Account(userID, user, password);
            }
            return account;
        } catch (SQLException e) {
            throw new LoginException("Oops, something went wrong in the database.");
        }
    }
}
