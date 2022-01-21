package me.liberty.ddd.core.service.impl;

import me.liberty.ddd.core.model.Account;
import me.liberty.ddd.core.model.AuthPolicy;
import me.liberty.ddd.core.model.Policy;
import me.liberty.ddd.core.service.AttachPermService;
import org.springframework.stereotype.Service;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 16:08
 */
@Service
public class AttachPermServiceImpl implements AttachPermService {

    @Override
    public void transferPerm(Account sourceAccount, Account targetAccount,
                             AuthPolicy sourceAuthPolicy, AuthPolicy targetAuthPolicy) {
        if ((sourceAccount instanceof Account.UserAccount && targetAccount instanceof Account.ApiAccount)
                ||
                (sourceAccount instanceof Account.ApiAccount && targetAccount instanceof Account.UserAccount)) {
            throw new RuntimeException("不同类型的账号之间不允许执行权限转移操作");
        }
        if (sourceAccount.isLocked() || targetAccount.isLocked()) {
            throw new RuntimeException("账号状态异常，不允许做权限转移");
        }
        // ... 其余的校验，比如授权范围是否合理等。

        for (Policy.Rule rule : sourceAuthPolicy.getRules()) {
            targetAuthPolicy.addRule(rule);
        }
        sourceAuthPolicy.recycled();
    }
}
