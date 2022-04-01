package me.liberty.ddd.dip.core.model;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.PolicyStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
        super();
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

    public static String parseSystemPerm(String resource) {
        if (StringUtils.isEmpty(resource)) {
            return null;
        }
        return resource.substring("rac:public:attach:permPolicy:".length());
    }

    private String buildResource(String policyName) {
        return "rac:public:attach:permPolicy:" + policyName;
    }

    public static AuthPolicy create(String policyId, String accountId, PolicyStatus status, List<Rule> rules) {
        AuthPolicy authPolicy = new AuthPolicy(accountId);
        authPolicy.policyId = policyId;
        authPolicy.status = status;

        if (!CollectionUtils.isEmpty(rules)) {
            authPolicy.rules = rules;
        }
        return authPolicy;
    }

}
