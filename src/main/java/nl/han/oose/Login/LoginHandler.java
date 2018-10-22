package nl.han.oose.Login;

public interface LoginHandler {
    Token login(LoginCredentials credentials) throws LoginException;
}
