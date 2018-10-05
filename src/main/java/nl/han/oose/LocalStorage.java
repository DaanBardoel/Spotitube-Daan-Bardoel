package nl.han.oose;

import java.util.ArrayList;
import java.util.List;

public class LocalStorage {

    private static List<Token> tokens = new ArrayList<>();

    public static List<Token> getTokens() {
        return tokens;
    }

    public static void setTokens(Token token) {
        LocalStorage.tokens.add(token);
    }
}
