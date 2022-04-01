package me.liberty.ddd.dip.core.model;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.PolicyStatus;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 12:11
 */
public abstract class Policy {
    @Getter
    protected String policyId;
    @Getter
    protected PolicyStatus status;

    public Policy() {
        this.policyId = UUID.randomUUID().toString();
        this.status = PolicyStatus.ASSIGNED;
    }

    public static class Rule {
        @Getter
        private String effect;
        @Getter
        private String[] action;
        @Getter
        private String resource;

        public Rule(String effect, String[] action, String resource) {
            this.effect = effect;
            this.action = action;
            this.resource = resource;
        }

        public boolean equal(Rule rule) {
            return resource.equals(rule.getResource())
                    && effect.equals(rule.getEffect())
                    && Arrays.equals(action, rule.getAction());
        }
    }
}
