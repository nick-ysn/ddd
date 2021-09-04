package me.liberty.ddd.common.dal.dao;

import me.liberty.ddd.common.dal.dos.UserAccountDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 00:00
 */
@Component
public interface UserAccountDAO extends JpaRepository<UserAccountDO, Integer> {

    UserAccountDO findByPhoneNo(String phoneNo);

    UserAccountDO findByAccountId(String accountId);
}
