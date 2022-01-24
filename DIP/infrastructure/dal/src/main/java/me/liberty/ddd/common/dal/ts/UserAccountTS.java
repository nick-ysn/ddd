package me.liberty.ddd.common.dal.ts;

import lombok.Getter;
import me.liberty.ddd.common.dal.dao.AuthPolicyDAO;
import me.liberty.ddd.common.dal.dao.PermPolicyDAO;
import me.liberty.ddd.common.dal.dao.RuleDAO;
import me.liberty.ddd.common.dal.dao.UserAccountDAO;
import me.liberty.ddd.common.dal.dos.AuthPolicyDO;
import me.liberty.ddd.common.dal.dos.RuleDO;
import me.liberty.ddd.common.dal.dos.UserAccountDO;
import me.liberty.ddd.common.util.enums.AccountStatus;
import me.liberty.ddd.common.util.enums.PolicyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * 描述事务脚本
 *
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 20:44
 */
public class UserAccountTS {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserAccountDAO userAccountDAO;
    @Autowired
    private RuleDAO ruleDAO;
    @Autowired
    private PermPolicyDAO permPolicyDAO;
    @Autowired
    private AuthPolicyDAO authPolicyDAO;

    public void create(UAccount account) {
        // 密码进行 md5 加密
        String password = DigestUtils.md5DigestAsHex(account.getPassword().getBytes());

        // ... 执行额外的一堆校验
        UAccount uAccount = jdbcTemplate.queryForObject("SELECT * FROM user_account WHERE phone_no = " + account.getPhoneNo(), UAccount.class);
        if (uAccount != null) {
            throw new RuntimeException("手机号已经存在，不允许新创建.....");
        }
        // ... 插入数据库
        String sql = "insert into  user_account (`name`, phone_no,`password`) values (" + account.getName() + "," + account.getPhoneNo() + "," + password + ")";
        jdbcTemplate.execute(sql);
    }

    public void createV2(UAccount account) {
        // 密码进行 md5 加密
        String password = DigestUtils.md5DigestAsHex(account.getPassword().getBytes());

        UserAccountDO userAccountDO = userAccountDAO.findByPhoneNo(account.getPhoneNo());
        if (userAccountDO != null) {
            throw new RuntimeException("手机号已经存在，不允许新创建.....");
        }
        userAccountDAO.save(new UserAccountDO(account.getName(), account.getPassword(), account.getPassword()));
    }

    public void transfer(String sourceAccountId, String targetAccountId) {
        UserAccountDO sourceAccountDO = userAccountDAO.findByAccountId(sourceAccountId);
        UserAccountDO targetAccountDO = userAccountDAO.findByAccountId(targetAccountId);

        if (sourceAccountDO.getStatus() == AccountStatus.LOCKED || targetAccountDO.getStatus() == AccountStatus.LOCKED) {
            throw new RuntimeException("账号状态异常，不允许执行权限转移操作");
        }
        AuthPolicyDO sourceAuthPolicyDO = authPolicyDAO.findByAccountId(sourceAccountId);
        AuthPolicyDO targetAuthPolicyDO = authPolicyDAO.findByAccountId(targetAccountId);
        List<RuleDO> sourceRuleDOS = ruleDAO.findByPolicyId(sourceAuthPolicyDO.getPolicyId());
        List<RuleDO> targetRuleDOs = ruleDAO.findByPolicyId(targetAuthPolicyDO.getPolicyId());

        //  授权关系拷贝
        for (RuleDO sourceRule : sourceRuleDOS) {
            boolean isMatch = targetRuleDOs.stream().anyMatch(ruleDO -> ruleDO.getResource() == sourceRule.getResource());
            if (!isMatch) {
                targetRuleDOs.add(sourceRule);
            }
        }

        // 回收旧权限
        sourceAuthPolicyDO.setStatus(PolicyStatus.RECYCLED);

        // 提交保存
        ruleDAO.saveAll(targetRuleDOs);
        authPolicyDAO.save(sourceAuthPolicyDO);
    }

    public static class UAccount {
        @Getter
        String accountId;
        @Getter
        String name;
        @Getter
        String phoneNo;
        @Getter
        String password;
    }
}
