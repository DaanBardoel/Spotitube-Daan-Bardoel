package nl.han.oose;

import nl.han.oose.entity.TokenDB;

import java.util.ArrayList;
import java.util.List;

public class LocalStorage {

    private static List<TokenDB> tokens = new ArrayList<>();

    public static List<TokenDB> getTokens() {
        return tokens;
    }

    public static void setTokens(TokenDB tokenDB) {
        LocalStorage.tokens.add(tokenDB);
    }
}
