package me.liberty.ddd.api.rpc.impl;

import me.liberty.ddd.api.rpc.UserAccountFacade;
import me.liberty.ddd.api.rpc.request.UserAccountRequest;
import me.liberty.ddd.api.rpc.response.Result;
import me.liberty.ddd.biz.service.account.UserAccountApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-10-03 13:15
 */
public class UserAccountFacadeImpl implements UserAccountFacade {

    @Autowired
    private UserAccountApplicationService userAccountApplicationService;

    public Result<String> createAccount(UserAccountRequest.CreateAccountRequest createAccountRequest) {

        String accountId = userAccountApplicationService.createAccount(createAccountRequest.getNickName(),
                createAccountRequest.getPhoneNo(),
                createAccountRequest.getPassword());

        return new Result<String>(true, accountId);
    }
}
