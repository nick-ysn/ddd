package me.liberty.ddd.biz.service.perm.impl;

import me.liberty.ddd.biz.service.perm.PermApplicationService;
import me.liberty.ddd.common.adapter.MessageAdapter;
import me.liberty.ddd.core.model.Account;
import me.liberty.ddd.core.model.AuthPolicy;
import me.liberty.ddd.core.service.AttachPermService;
import me.liberty.ddd.repository.AccountRepository;
import me.liberty.ddd.repository.AuthPolicyRepository;
import me.liberty.ddd.repository.SystemPermPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:32
 */
public class PermApplicationServiceImpl implements PermApplicationService {

    @Autowired
    private SystemPermPolicyRepository systemPermPolicyRepository;
    @Autowired
    private AuthPolicyRepository authPolicyRepository;
    @Autowired
    private MessageAdapter messageAdapter;
    @Autowired
    private AttachPermService attachPermService;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void attach(String accountId, String policyName) {

        boolean hasPermPolicy = systemPermPolicyRepository.hasPermPolicyOfName(policyName);
        if (!hasPermPolicy) {
            throw new RuntimeException("权限策略不存在，不允许执行授权操作");
        }

        boolean hasAuthPolicy = authPolicyRepository.hasAuthPolicy(accountId);
        AuthPolicy authPolicy = hasAuthPolicy ? authPolicyRepository.authPolicyOfAccount(accountId) : new AuthPolicy(accountId);

        // 执行授权操作
        authPolicy.attach(policyName);

        // 发送授权成功消息
        messageAdapter.attachedOfPolicy(accountId, policyName);

        authPolicyRepository.save(authPolicy);

    }

    @Transactional
    public void transfer(String sourceAccountId, String targetAccountId) {

        Account sourceAccount = accountRepository.accountOfId(sourceAccountId);
        Account targetAccount = accountRepository.accountOfId(targetAccountId);
        AuthPolicy sourceAuthPolicy = authPolicyRepository.authPolicyOfAccount(sourceAccountId);
        AuthPolicy targetAuthPolicy = authPolicyRepository.authPolicyOfAccount(targetAccountId);

        // 1. 通过验证码或者密码校验
        // .....

        // 2. 执行权限转移
        attachPermService.transferPerm(sourceAccount, targetAccount, sourceAuthPolicy, targetAuthPolicy);

        //  3. 发送通知
        messageAdapter.transferOfPolicy(sourceAccountId, targetAccountId);

        accountRepository.save(sourceAccount);
        authPolicyRepository.save(targetAuthPolicy);

    }

}
