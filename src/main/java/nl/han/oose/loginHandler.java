package nl.han.oose;

public class loginHandler {


    public Token login(LoginCredentials credentials) throws LoginException {
        //create exception with if-else statement
        if (credentials.getUser().equals("daan") && credentials.getPassword().equals("password")) {
            return new Token("1234-1234-1234", credentials.getUser());
        } else {
            throw new LoginException("credentials not correct!");
        }
    }
}