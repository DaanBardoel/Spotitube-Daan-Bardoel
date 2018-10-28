package nl.han.oose.Login;

public interface LoginService {
//    TokenOnlyForReturn login(LoginCredentials credentials) throws LoginException;

    TokenOnlyForReturn loginNewVersion(LoginCredentials credentials) throws LoginException;
}
