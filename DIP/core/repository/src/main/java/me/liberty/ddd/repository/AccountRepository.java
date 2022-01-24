package me.liberty.ddd.repository;

import me.liberty.ddd.dip.core.model.Account;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:05
 */
public interface AccountRepository {

    void save(Account account);

    boolean hasAccountOfPhone(String phoneNo);

    Account accountOfId(String accountId);
}
