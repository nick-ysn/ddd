package me.liberty.ddd.common.dal.dao;

import me.liberty.ddd.common.dal.dos.AuthPolicyDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 10:46
 */
public interface AuthPolicyDAO extends JpaRepository<AuthPolicyDO, Integer> {

    AuthPolicyDO findByAccountId(String accountId);

    AuthPolicyDO findByPolicyId(String policyId);
}
