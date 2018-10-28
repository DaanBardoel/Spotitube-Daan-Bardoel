//package nl.han.oose.Login;
//
//import nl.han.oose.LocalStorage;
//import nl.han.oose.Persistence.AccountDAO;
//import nl.han.oose.Persistence.TokenDAO;
//import nl.han.oose.entity.Account;
//import nl.han.oose.entity.TokenOnlyForReturn;
//
//import javax.enterprise.inject.Alternative;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//@Alternative
//public class LoginHandlerAlternativeImpl implements LoginService {
//
//    @Override
//    public TokenOnlyForReturn login(LoginCredentials credentials) throws LoginException {
//
//        Account account = new Account(credentials.getUser(), credentials.getPassword());
//        AccountDAO dao = new AccountDAO();
//        List<Account> accountsList = dao.getAllAccounts();
//        for (Account accountIndex : accountsList) {
//            if (accountIndex.getUser().equals(account.getUser()) && accountIndex.getPassword().equals(account.getPassword())) {
//                TokenDAO tdao = new TokenDAO();
//                List<TokenOnlyForReturn> tokenList = tdao.getAllTokens();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
//                Date date = new Date();
//                int length = 14;
//                String dateString = sdf.format(date);
//
//                for (TokenOnlyForReturn tokenIndex : tokenList) {
//                    if (tokenIndex.getuser().equals(accountIndex.getUser())) {
//                        String databaseDateString = tokenIndex.getDateString();
//                        try {
//                            Date databaseDate = sdf.parse(databaseDateString);
//                            if (date.after(databaseDate)) {
//                                tdao.deleteToken(tokenIndex);
//                                TokenOnlyForReturn token = new TokenOnlyForReturn(accountIndex.getUser(), this.getToken(length), dateString);
//                                tdao.persistToken(token);
//                                return token;
//                            } else {
//                                return tokenIndex;
//                            }
//                        } catch (ParseException e) {
//                            throw new LoginException("incorrect date in database");
//                        }
//                    }
//                }
//                TokenOnlyForReturn token = new TokenOnlyForReturn(accountIndex.getUser(), this.getToken(length),dateString);
//                tdao.persistToken(token);
//                return token;
//            }
//        }
//        throw new LoginException("credentials not correct!");
//    }
//
//    @Override
//    public String getToken(int length) {
//        StringBuilder token = new StringBuilder(length);
//        Random random = new Random();
//        for (int i = 0; i < length; i++) {
//            String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890-";
//            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
//        }
//        return token.toString();
//    }
//}
