package me.liberty.ddd.core.model;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.PolicyStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 12:14
 */
public class AuthPolicy extends Policy {

    @Getter
    private String accountId;
    @Getter
    private List<Rule> rules;

    public AuthPolicy(String accountId) {
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
    }

    public void addRule(Rule targetRule) {
        boolean isPresent = this.rules.stream().anyMatch(rule -> rule.equal(targetRule));
        if (!isPresent) {
            this.rules.add(targetRule);
        }
    }

    public void detach(String policyName) {
        String resource = buildResource(policyName);
        this.rules.removeIf(rule -> rule.getResource().equals(resource));
    }

    public void recycled() {
        if (this.status == PolicyStatus.ASSIGNED) {
            this.status = PolicyStatus.RECYCLED;
        }
    }

    private String buildResource(String policyName) {
        return "rac:public:attach:permPolicy:" + policyName;
    }
}
