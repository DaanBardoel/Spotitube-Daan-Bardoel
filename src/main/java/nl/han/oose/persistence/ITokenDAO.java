package nl.han.oose.persistence;

import nl.han.oose.entity.Token;

import java.util.List;

public interface ITokenDAO {
    List<Token> getAllTokens();

    void persistToken(Token token);

    void deleteToken(Token token);

    Token getTokenForUserId(int userID);

    Token getTokenForGivenTokenString(String tokenString);
}
