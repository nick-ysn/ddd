package me.liberty.ddd.common.dal.dao;

import me.liberty.ddd.common.dal.dos.OperateRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-12-27 16:33
 */
public interface OperateRecordDAO extends JpaRepository<OperateRecordDO, Integer> {
}
