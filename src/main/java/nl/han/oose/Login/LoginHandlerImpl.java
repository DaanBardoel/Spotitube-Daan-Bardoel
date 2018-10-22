package nl.han.oose.Login;

import nl.han.oose.Persistence.AccountDAO;
import nl.han.oose.Persistence.TokenDAO;
import nl.han.oose.entity.AccountDB;
import nl.han.oose.entity.TokenDB;

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
    public TokenOnlyReturnValuesForResponse login(LoginCredentials credentials) throws LoginException {
        AccountOnlyCredentials account = new AccountOnlyCredentials(credentials.getUser(), credentials.getPassword());
        AccountDAO dao = new AccountDAO();
        List<AccountDB> accountsList = dao.getAllAccounts();
        for (AccountDB accountDBIndex : accountsList) {
            if (accountDBIndex.getUser().equals(account.getUser()) && accountDBIndex.getPassword().equals(account.getPassword())) {
                TokenDAO tdao = new TokenDAO();
                List<TokenDB> tokenDBList = tdao.getAllTokens();
                for (TokenDB tokenDBIndex : tokenDBList) {
                    if (tokenDBIndex.getuser() == accountDBIndex.getUserId()) {
                        String databaseDateString = tokenDBIndex.getDateString();
                        Date date = new Date();
                        if (date.after(this.getDatabaseDate(databaseDateString))) {
                            tdao.deleteToken(tokenDBIndex);
                            TokenDB tokenDB = new TokenDB(this.getToken(), accountDBIndex.getUserId(), this.getInsertIntoDatabaseString());
                            tdao.persistToken(tokenDB);
                            return new TokenOnlyReturnValuesForResponse(tokenDB.getToken(), tokenDB.getuser());
                        } else {
                            return new TokenOnlyReturnValuesForResponse(tokenDBIndex.getToken(), tokenDBIndex.getuser());
                        }
                    }
                }
                TokenDB tokenDB = new TokenDB(this.getToken(), accountDBIndex.getUserId(), this.getInsertIntoDatabaseString());
                tdao.persistToken(tokenDB);
                return new TokenOnlyReturnValuesForResponse(tokenDB.getToken(), tokenDB.getuser());
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

    private String getInsertIntoDatabaseString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int validUntilTimeForNewToken = 1;
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(validUntilTimeForNewToken);
        Date insertDatabaseDate = Date.from(tomorrow.atZone(ZoneId.systemDefault()).toInstant());
        return sdf.format(insertDatabaseDate);
    }

    private Date getDatabaseDate(String databaseDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            return sdf.parse(databaseDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}