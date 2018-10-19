package nl.han.oose.Login;

import nl.han.oose.Persistence.AccountDAO;
import nl.han.oose.Persistence.TokenDAO;
import nl.han.oose.entity.Account;
import nl.han.oose.entity.Token;

import javax.enterprise.inject.Default;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Default
public class LoginHandlerImpl implements LoginHandler {

    @Override
    public Token login(LoginCredentials credentials) throws LoginException {
        User account = new User(credentials.getUser(), credentials.getPassword());
        AccountDAO dao = new AccountDAO();
        List<Account> accountsList = dao.getAllAccounts();
        for (Account accountIndex : accountsList) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            if (accountIndex.getUser().equals(account.getUser()) && accountIndex.getPassword().equals(account.getPassword())) {
                TokenDAO tdao = new TokenDAO();
                List<Token> tokenList = tdao.getAllTokens();
                for (Token tokenIndex : tokenList) {
                    if (tokenIndex.getuser() == accountIndex.getUserId()) {
                        String databaseDateString = tokenIndex.getDateString();
                        try {
                            Date databaseDate = sdf.parse(databaseDateString);
                            Date date = new Date();
                            if (date.after(databaseDate)) {
                                tdao.deleteToken(tokenIndex);

                                Token token = new Token(this.getToken(), accountIndex.getUserId(), this.getInsertIntoDatabaseString(sdf));
                                tdao.persistToken(token);
                                return token;
                            } else {
                                return tokenIndex;
                            }
                        } catch (ParseException e) {
                            throw new LoginException("incorrect date in database");
                        }
                    }
                }
                Token token = new Token(this.getToken(), accountIndex.getUserId(), this.getInsertIntoDatabaseString(sdf));
                tdao.persistToken(token);
                return token;
            }
        }
        throw new LoginException("credentials not correct!");
    }

    private String getToken() {
        int length = 14;
        StringBuilder token = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890-";
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
    }

    private String getInsertIntoDatabaseString(SimpleDateFormat sdf) {
        int validUntilTimeForNewToken = 1;
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(validUntilTimeForNewToken);
        Date insertDatabaseDate = Date.from(tomorrow.atZone(ZoneId.systemDefault()).toInstant());
        return sdf.format(insertDatabaseDate);
    }
}