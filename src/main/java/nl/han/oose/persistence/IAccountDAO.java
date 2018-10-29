package nl.han.oose.persistence;

import nl.han.oose.entity.Account;
import nl.han.oose.entity.dto.AccountDTO;

import java.util.List;

public interface IAccountDAO {
    List<Account> getAllAccounts();

    Account getAccountForGivenCredentials(AccountDTO credentials);
}
