package nl.han.oose.Persistence;

import nl.han.oose.Login.LoginCredentials;
import nl.han.oose.Login.LoginException;
import nl.han.oose.entity.AccountDB;

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
            throw new LoginException("Oops, something went wrong in the database.");
        }
        return accountDBS;
    }

    @Override
    public AccountDB getAccountForGivenCredentials(LoginCredentials credentials) {
        AccountDB account;
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
                account = new AccountDB(userID, user, password);
            }
            return account;
        } catch (SQLException e) {
            throw new LoginException("Oops, something went wrong in the database.");
        }
    }
}
