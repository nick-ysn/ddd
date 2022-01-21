package me.liberty.ddd.biz.service.account.impl;

import me.liberty.ddd.biz.service.account.UserAccountApplicationService;
import me.liberty.ddd.core.model.Account;
import me.liberty.ddd.core.model.AuthPolicy;
import me.liberty.ddd.core.service.AttachPermService;
import me.liberty.ddd.repository.AccountRepository;
import me.liberty.ddd.repository.AuthPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-09 18:02
 */
@Service
public class UserAccountApplicationServiceImpl implements UserAccountApplicationService {

    @Autowired
   private AccountRepository accountRepository;

    @Autowired
    private AttachPermService attachPermService;

    @Autowired
    private AuthPolicyRepository authPolicyRepository;

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

    @Override
    public void discardAccount(String accountId) {

        Account sourceAccount = accountRepository.accountOfId(accountId);

        // 根据组织结构获取当前用户的主管信息
        Account leaderAccount = null; // 从组织结构的服务里获取账号信息

        AuthPolicy sourceAuthPolicy = authPolicyRepository.authPolicyOfAccount(accountId);
        AuthPolicy targetAuthPolicy = authPolicyRepository.authPolicyOfAccount(leaderAccount.getAccountId());

        attachPermService.transferPerm(sourceAccount, leaderAccount, sourceAuthPolicy, targetAuthPolicy);

        sourceAccount.assigned();

        accountRepository.save(sourceAccount);
        authPolicyRepository.save(targetAuthPolicy);

    }
}
