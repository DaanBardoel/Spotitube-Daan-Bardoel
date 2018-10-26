package nl.han.oose.Persistence;

import nl.han.oose.Login.LoginCredentials;
import nl.han.oose.entity.AccountDB;

import java.util.List;

public interface IAccountDAO {
    List<AccountDB> getAllAccounts();

    AccountDB getAccountForGivenCredentials(LoginCredentials credentials);
}
