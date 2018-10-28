package nl.han.oose.Persistence;

import nl.han.oose.entity.TokenDB;

import java.util.List;

public interface ITokenDAO {
    List<TokenDB> getAllTokens();

    void persistToken(TokenDB tokenDB);

    void deleteToken(TokenDB tokenDB);

    TokenDB getTokenForUserId(int userID);

    TokenDB getTokenForGivenTokenString(String tokenString);
}
