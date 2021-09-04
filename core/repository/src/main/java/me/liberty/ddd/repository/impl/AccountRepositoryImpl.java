package me.liberty.ddd.repository.impl;

import me.liberty.ddd.core.model.Account;
import me.liberty.ddd.repository.AccountRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:05
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public void save(Account account) {

    }

    @Override
    public boolean hasAccountOfPhone(String phoneNo) {
        return false;
    }

    @Override
    public Account accountOfId(String accountId) {
        return null;
    }
}
