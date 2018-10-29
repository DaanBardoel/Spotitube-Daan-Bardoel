package nl.han.oose.Persistence;

import nl.han.oose.entity.DTO.AccountDTO;
import nl.han.oose.entity.Account;

import java.util.List;

public interface IAccountDAO {
    List<Account> getAllAccounts();

    Account getAccountForGivenCredentials(AccountDTO credentials);
}
