package me.liberty.ddd.biz.service.impl;

import me.liberty.ddd.biz.service.AccountApplicationService;
import me.liberty.ddd.core.model.Account;
import me.liberty.ddd.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:14
 */
@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public String createAccount(String nickName, String phoneNo, String password) {

        boolean hasAccount = accountRepository.hasAccountOfPhone(phoneNo);
        if (hasAccount) {
            throw new RuntimeException("同一个手机号只能注册一个账号");
        }

        Account.UserAccount userAccount = new Account.UserAccount(nickName, phoneNo, password);
        accountRepository.save(userAccount);
        return userAccount.getAccountId();
    }
}
