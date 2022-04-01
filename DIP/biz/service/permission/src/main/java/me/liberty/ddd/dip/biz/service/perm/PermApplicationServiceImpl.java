package me.liberty.ddd.dip.biz.service.perm;

import me.liberty.ddd.dip.core.model.Account;
import me.liberty.ddd.dip.core.model.AuthPolicy;
import me.liberty.ddd.dip.corruption.MessageAdapter;
import me.liberty.ddd.dip.repository.AccountRepository;
import me.liberty.ddd.dip.repository.AuthPolicyRepository;
import me.liberty.ddd.dip.repository.SystemPermPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:32
 */
public class PermApplicationServiceImpl {

    @Autowired
    private SystemPermPolicyRepository systemPermPolicyRepository;
    @Autowired
    private AuthPolicyRepository authPolicyRepository;
    @Autowired
    private MessageAdapter messageAdapter;
    @Autowired
    private AccountRepository accountRepository;

    public void transfer(String sourceAccountId, String targetAccountId) {

        Account.UserAccount sourceAccount = accountRepository.accountOfId(sourceAccountId);
        Account.UserAccount targetAccount = accountRepository.accountOfId(targetAccountId);
        AuthPolicy sourceAuthPolicy = authPolicyRepository.authPolicyOfAccount(sourceAccountId);
        AuthPolicy targetAuthPolicy = authPolicyRepository.authPolicyOfAccount(targetAccountId);

        // 1. 通过验证码或者密码校验
        // .....

        // 2. 执行权限转移
        // attachPermService.transferPerm(sourceAccount, targetAccount, sourceAuthPolicy, targetAuthPolicy);

        //  3. 发送通知
        messageAdapter.transferOfPolicy(sourceAccount, targetAccount);

        accountRepository.save(sourceAccount);
        authPolicyRepository.save(targetAuthPolicy);

    }

}
