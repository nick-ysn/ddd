package me.liberty.ddd.dip.corruption;

import me.liberty.ddd.dip.core.model.Account;
import me.liberty.ddd.dip.core.model.AuthPolicy;

/**
 * @author yuanshouna@gmail.com
 * @created 2022-01-24 11:38
 */
public interface MessageAdapter {

    void createdOfAccount();

    void attachedOfPolicy(AuthPolicy authPolicy);

    void transferOfPolicy(Account.UserAccount sourceAccount, Account.UserAccount targetAccount);
}
