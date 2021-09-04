package me.liberty.ddd.core.model;

import lombok.Getter;
import me.liberty.ddd.common.adapter.MessageAdapter;
import me.liberty.ddd.common.util.enums.PolicyStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 12:14
 */
public class AuthPolicyTX extends Policy {

    @Getter
    private String accountId;
    @Getter
    private List<Rule> rules;
    private MessageAdapter messageAdapter;

    public AuthPolicyTX(String accountId) {
        this.accountId = accountId;
        this.rules = new ArrayList<>();
    }

    // 为某个账号授予新的权限策略
    public void attach(String policyName) {
        String resource = buildResource(policyName);
        for (Rule rule : rules) {
            if (resource.equals(rule.getResource())) {
                // 已经授权，不重复操作
                return;
            }
        }
        this.rules.add(new Rule("allow", new String[]{"authPermPolicy"}, resource));

        // 调用消息 api 通知用户
        messageAdapter.attachedOfPolicy(accountId, policyName);

        // ...

    }



    private String buildResource(String policyName) {
        return "rac:public:attach:permPolicy:" + policyName;
    }
}
