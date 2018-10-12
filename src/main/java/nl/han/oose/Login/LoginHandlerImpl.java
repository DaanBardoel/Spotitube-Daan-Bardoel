package nl.han.oose.Login;

import nl.han.oose.LocalStorage;
import nl.han.oose.Persistence.AccountDAO;
import nl.han.oose.Token;
import nl.han.oose.entity.Account;

import javax.enterprise.inject.Default;
import java.util.List;

@Default
public class LoginHandlerImpl implements LoginHandler {

    @Override
    public Token login(LoginCredentials credentials) throws LoginException {
        //create exception with if-else statement
//        if (credentials.getUser().equals("daan") && credentials.getPassword().equals("password")) {
//            Token token = new Token("1234-1234-1234", credentials.getUser());
//            LocalStorage.setTokens(token);
//            return token;
//        } else {
//            throw new LoginException("credentials not correct!");
//        }
        Account account = new Account(credentials.getUser(), credentials.getPassword());
        AccountDAO dao = new AccountDAO();
        List<Account> accountsList = dao.getAllAccounts();
        for (Account accountIndex : accountsList) {
            if (accountIndex.getUser().equals(account.getUser()) && accountIndex.getPassword().equals(account.getPassword())) {
                Token token = new Token("1234-1234-1234", credentials.getUser());
                LocalStorage.setTokens(token);
                return token;
            }
        }
            throw new LoginException("credentials not correct!");
    }
}