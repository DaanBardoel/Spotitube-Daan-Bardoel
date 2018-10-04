package nl.han.oose;

public class loginHandler {


    public Token login(LoginCredentials credentials) throws LoginException {
        //create exception with if-else statement
        Token token;
        if (credentials.getUser().equals("daan") && credentials.getPassword().equals("password")) {
            token = new Token("1234-1234-1234", credentials.getUser());
        } else {
            throw new LoginException("credentials not correct!");
        }
        return token;
    }
}