package me.liberty.ddd.common.adapter.impl;

import me.liberty.ddd.common.adapter.MessageAdapter;
import org.springframework.stereotype.Component;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 15:05
 */
@Component
public class MessageAdapterImpl implements MessageAdapter {

    @Override
    public void createdOfAccount() {

    }

    @Override
    public void attachedOfPolicy(String accountId, String policyName) {

    }

    @Override
    public void transferOfPolicy(String srouceAccountId, String targetAccountId) {

    }
}
