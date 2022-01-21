package me.liberty.ddd.common.dal.dao;

import me.liberty.ddd.common.dal.dos.RuleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 10:35
 */
@Component
public interface RuleDAO extends JpaRepository<RuleDO, Integer> {

    List<RuleDO> findByPolicyId(String policyId);
}