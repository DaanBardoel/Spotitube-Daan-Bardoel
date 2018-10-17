package nl.han.oose.Login;

import nl.han.oose.entity.Token;

public interface LoginHandler {
    Token login(LoginCredentials credentials) throws LoginException;
}
