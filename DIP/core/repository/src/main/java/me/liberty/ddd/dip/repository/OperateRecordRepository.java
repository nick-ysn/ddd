package me.liberty.ddd.dip.repository;

import me.liberty.ddd.core.model.OperateRecord;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-12-27 16:42
 */
public interface OperateRecordRepository {
    void save(OperateRecord operateRecord);
}
