package nl.han.oose.service;

import nl.han.oose.entity.dto.AccountDTO;
import nl.han.oose.entity.dto.TokenDTO;
import nl.han.oose.exceptions.LoginException;

public interface LoginService {
//    TokenDTO login(AccountDTO credentials) throws LoginException;

    TokenDTO loginNewVersion(AccountDTO credentials) throws LoginException;
}
