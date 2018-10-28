package nl.han.oose.Login;

import nl.han.oose.Persistence.IAccountDAO;
import nl.han.oose.Persistence.ITokenDAO;
import nl.han.oose.entity.AccountDB;
import nl.han.oose.entity.TokenDB;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    IAccountDAO accountDAO;

    @Inject
    ITokenDAO tokenDAO;

    @Override
    public TokenOnlyForReturn loginNewVersion(LoginCredentials credentials) throws LoginException {
        AccountDB account = accountDAO.getAccountForGivenCredentials(credentials);
        if (account == null) {
            throw new LoginException("gegevens niet correct!");
        } else {
            return this.getTokenOnlyForReturn(account);
        }
    }

    private TokenOnlyForReturn getTokenOnlyForReturn(AccountDB account) {
        TokenDB tokenDB = tokenDAO.getTokenForUserId(account.getUserId());
        if (tokenDB == null) {
            tokenDAO.persistToken(new TokenDB(this.getTokenString(), account.getUserId(), this.getInsertIntoDatabaseString()));
            return new TokenOnlyForReturn(this.getTokenString(), account.getUser());
        } else {
            Date date = new Date();
            if (date.after(this.getDatabaseDate(tokenDB.getDateString()))) {
                tokenDAO.deleteToken(tokenDB);
                TokenDB tokenIntoDatabase = new TokenDB(this.getTokenString(), account.getUserId(), this.getInsertIntoDatabaseString());
                return new TokenOnlyForReturn(tokenIntoDatabase.getToken(), account.getUser());
            } else {
                return new TokenOnlyForReturn(tokenDB.getToken(), account.getUser());
            }
        }
    }

    private String getTokenString() {
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
            throw new LoginException("Unable to parse date from database. Incorrect date for this token.");
        }
    }
}