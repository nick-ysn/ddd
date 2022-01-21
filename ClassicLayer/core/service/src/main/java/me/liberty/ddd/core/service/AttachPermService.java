package me.liberty.ddd.core.service;

import me.liberty.ddd.core.model.Account;
import me.liberty.ddd.core.model.AuthPolicy;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 17:22
 */
public interface AttachPermService {
    void transferPerm(Account sourceAccount, Account targetAccount,
                      AuthPolicy sourceAuthPolicy, AuthPolicy targetAuthPolicy);
}
