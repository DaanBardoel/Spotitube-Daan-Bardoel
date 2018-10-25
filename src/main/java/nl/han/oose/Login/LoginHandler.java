package nl.han.oose.Login;

public interface LoginHandler {
    TokenOnlyForReturn login(LoginCredentials credentials) throws LoginException;
}
