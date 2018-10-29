package nl.han.oose.service;

import nl.han.oose.entity.Account;
import nl.han.oose.entity.Token;
import nl.han.oose.entity.dto.AccountDTO;
import nl.han.oose.entity.dto.TokenDTO;
import nl.han.oose.exceptions.LoginException;
import nl.han.oose.persistence.IAccountDAO;
import nl.han.oose.persistence.ITokenDAO;

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
    public TokenDTO loginNewVersion(AccountDTO credentials) throws LoginException {
        Account account = accountDAO.getAccountForGivenCredentials(credentials);
        if (account == null) {
            throw new LoginException("Incorrect credentials!");
        } else {
            return this.getTokenOnlyForReturn(account);
        }
    }

    private TokenDTO getTokenOnlyForReturn(Account account) {
        Token token = tokenDAO.getTokenForUserId(account.getUserId());
        if (token == null) {
            tokenDAO.persistToken(new Token(this.getTokenString(), account.getUserId(), this.getInsertIntoDatabaseString()));
            return new TokenDTO(this.getTokenString(), account.getUser());
        } else {
            Date date = new Date();
            if (date.after(this.getDatabaseDate(token.getDateString()))) {
                tokenDAO.deleteToken(token);
                Token tokenIntoDatabase = new Token(this.getTokenString(), account.getUserId(), this.getInsertIntoDatabaseString());
                return new TokenDTO(tokenIntoDatabase.getToken(), account.getUser());
            } else {
                return new TokenDTO(token.getToken(), account.getUser());
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