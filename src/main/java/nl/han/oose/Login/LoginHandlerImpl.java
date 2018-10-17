package nl.han.oose.Login;

import nl.han.oose.Persistence.AccountDAO;
import nl.han.oose.entity.Account;
import nl.han.oose.entity.Token;

import javax.enterprise.inject.Default;
import java.util.List;

@Default
public class LoginHandlerImpl implements LoginHandler {

    @Override
    public Token login(LoginCredentials credentials) throws LoginException {

        Account account = new Account(credentials.getUser(), credentials.getPassword());
        AccountDAO dao = new AccountDAO();
        List<Account> accountsList = dao.getAllAccounts();
        for (Account accountIndex : accountsList) {
            if (accountIndex.getUser().equals(account.getUser()) && accountIndex.getPassword().equals(account.getPassword())) {
//                Token token = new Token("1234-1234-1234", credentials.getUser());
//                LocalStorage.setTokens(token);
//                return token;

            }
        }
            throw new LoginException("credentials not correct!");
    }
}