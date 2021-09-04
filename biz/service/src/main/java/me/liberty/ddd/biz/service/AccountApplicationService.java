package me.liberty.ddd.biz.service;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:12
 */
public interface AccountApplicationService {

    String createAccount(String nickName, String phoneNo, String password);
}
