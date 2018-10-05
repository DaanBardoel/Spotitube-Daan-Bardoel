package nl.han.oose.Login;

import nl.han.oose.Token;

import javax.enterprise.inject.Alternative;

@Alternative
public class LoginHandlerAlternativeImpl implements LoginHandler {

    @Override
    public Token login(LoginCredentials credentials) throws LoginException {
        //create exception with if-else statement
        if (credentials.getUser().equals("hans") && credentials.getPassword().equals("wurst")) {
            return new Token("1234-1234-1234", credentials.getUser());
        } else {
            throw new LoginException("credentials not correct!");
        }
    }
}
