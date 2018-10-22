package nl.han.oose.Login;

public interface LoginHandler {
    TokenOnlyReturnValuesForResponse login(LoginCredentials credentials) throws LoginException;
}
