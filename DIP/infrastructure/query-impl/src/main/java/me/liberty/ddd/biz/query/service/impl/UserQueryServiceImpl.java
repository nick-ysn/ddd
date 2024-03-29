package me.liberty.ddd.biz.query.service.impl;

import me.liberty.ddd.biz.query.dto.UserDTO;
import me.liberty.ddd.biz.query.service.UserQueryService;
import me.liberty.ddd.common.dal.dao.AuthPolicyDAO;
import me.liberty.ddd.common.dal.dao.PermPolicyDAO;
import me.liberty.ddd.common.dal.dao.RuleDAO;
import me.liberty.ddd.common.dal.dao.UserAccountDAO;
import me.liberty.ddd.common.dal.dos.AuthPolicyDO;
import me.liberty.ddd.common.dal.dos.RuleDO;
import me.liberty.ddd.common.dal.dos.UserAccountDO;
import me.liberty.ddd.core.model.AuthPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-10 17:57
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserAccountDAO userAccountDAO;
    @Autowired
    private AuthPolicyDAO authPolicyDAO;
    @Autowired
    private PermPolicyDAO permPolicyDAO;
    @Autowired
    private RuleDAO ruleDAO;

    @Override
    public UserDTO queryBaseInfo(String accountId) {

        UserAccountDO accountDO = userAccountDAO.findByAccountId(accountId);
        AuthPolicyDO authPolicyDO = authPolicyDAO.findByAccountId(accountId);

        List<RuleDO> ruleDOS = ruleDAO.findByPolicyId(authPolicyDO.getPolicyId());

        List<String> systemPermPolicies = ruleDOS.stream()
                .map(ruleDO -> AuthPolicy.parseSystemPerm(ruleDO.getResource()))
                .collect(Collectors.toList());

        return new UserDTO(
                accountId,
                accountDO.getNickName(),
                accountDO.getPhoneNo(),
                accountDO.getPassword(),
                systemPermPolicies,
                accountDO.getGmtCreated(),
                accountDO.getGmtModified()
        );
    }
}
