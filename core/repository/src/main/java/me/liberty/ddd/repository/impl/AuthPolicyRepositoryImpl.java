package me.liberty.ddd.repository.impl;

import me.liberty.ddd.common.dal.dao.AuthPolicyDAO;
import me.liberty.ddd.common.dal.dao.RuleDAO;
import me.liberty.ddd.common.dal.dos.AuthPolicyDO;
import me.liberty.ddd.common.dal.dos.RuleDO;
import me.liberty.ddd.core.model.AuthPolicy;
import me.liberty.ddd.core.model.Policy;
import me.liberty.ddd.repository.AuthPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-06 10:57
 */
public class AuthPolicyRepositoryImpl implements AuthPolicyRepository {

    @Autowired
    private AuthPolicyDAO authPolicyDAO;
    @Autowired
    private RuleDAO ruleDAO;

    @Override
    public void save(AuthPolicy authPolicy) {
        AuthPolicyDO authPolicyDO = new AuthPolicyDO();
        authPolicyDO.setStatus(authPolicy.getStatus());
        authPolicyDO.setPolicyId(authPolicy.getPolicyId());
        authPolicyDO.setAccountId(authPolicy.getAccountId());

        ArrayList<RuleDO> ruleDOS = new ArrayList<>();
        for (Policy.Rule rule : authPolicy.getRules()) {
            RuleDO ruleDO = new RuleDO();
            ruleDO.setPolicyId(authPolicyDO.getPolicyId());
            ruleDO.setAction(String.join(",", rule.getAction()));
            ruleDO.setEffect(rule.getEffect());
            ruleDO.setResource(rule.getResource());
            ruleDOS.add(ruleDO);
        }
        AuthPolicyDO checkDO = authPolicyDAO.findByPolicyId(authPolicyDO.getPolicyId());
        if (checkDO != null) {
            authPolicyDO.setId(checkDO.getId());
        }
        authPolicyDAO.save(authPolicyDO);
        ruleDAO.saveAll(ruleDOS);
    }

    @Override
    public boolean hasAuthPolicy(String accountId) {
        return false;
    }

    @Override
    public AuthPolicy authPolicyOfAccount(String accountId) {
        AuthPolicyDO authPolicyDO = authPolicyDAO.findByAccountId(accountId);
        if (authPolicyDO == null) {
            throw new RuntimeException("目标账号目前没有授权策略");
        }
        List<RuleDO> ruleDOS = ruleDAO.findByPolicyId(authPolicyDO.getPolicyId());
        List<Policy.Rule> rules = ruleDOS.stream().map(ruleDO -> new Policy.Rule(
                ruleDO.getEffect(),
                ruleDO.getAction().split(","),
                ruleDO.getResource()
        )).collect(Collectors.toList());

        return AuthPolicy.create(
                authPolicyDO.getPolicyId(),
                authPolicyDO.getAccountId(),
                authPolicyDO.getStatus(), rules
        );
    }
}
