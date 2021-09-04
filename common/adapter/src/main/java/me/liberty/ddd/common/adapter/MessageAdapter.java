package me.liberty.ddd.common.adapter;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 15:09
 */
public interface MessageAdapter {
    void createdOfAccount();

    void attachedOfPolicy(String accountId, String policyName);

    void transferOfPolicy(String srouceAccountId, String targetAccountId);
}
