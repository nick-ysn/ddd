package me.liberty.ddd.biz.service.account;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-09 18:03
 */
public interface UserAccountApplicationService {
    String createAccount(String nickName, String phoneNo, String password);

    void discardAccount(String accountId);
}
