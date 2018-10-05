package nl.han.oose.Login;

import nl.han.oose.Token;

import javax.enterprise.inject.Default;

@Default
public class LoginHandlerImpl implements LoginHandler {

    @Override
    public Token login(LoginCredentials credentials) throws LoginException {
        //create exception with if-else statement
        if (credentials.getUser().equals("daan") && credentials.getPassword().equals("password")) {
            return new Token("1234-1234-1234", credentials.getUser());
        } else {
            throw new LoginException("credentials not correct!");
        }
    }
}