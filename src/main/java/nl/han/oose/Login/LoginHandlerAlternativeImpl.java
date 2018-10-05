package nl.han.oose.Login;

import nl.han.oose.LocalStorage;
import nl.han.oose.Token;

import javax.enterprise.inject.Alternative;

@Alternative
public class LoginHandlerAlternativeImpl implements LoginHandler {

    @Override
    public Token login(LoginCredentials credentials) throws LoginException {
        //create exception with if-else statement
        if (credentials.getUser().equals("hans") && credentials.getPassword().equals("wurst")) {
            Token token = new Token("1234-1234-1234", credentials.getUser());
            LocalStorage.setTokens(token);
            return token;
        } else {
            throw new LoginException("credentials not correct!");
        }
    }
}
