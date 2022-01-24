package me.liberty.ddd.common.dal.dao;

import me.liberty.ddd.common.dal.dos.PermPolicyDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 10:43
 */
public interface PermPolicyDAO extends JpaRepository<PermPolicyDO, Integer> {
}
