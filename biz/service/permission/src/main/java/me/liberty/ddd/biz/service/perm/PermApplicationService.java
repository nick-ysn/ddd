package me.liberty.ddd.biz.service.perm;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 15:12
 */
public interface PermApplicationService {
    void attach(String accountId, String policyName);
}
