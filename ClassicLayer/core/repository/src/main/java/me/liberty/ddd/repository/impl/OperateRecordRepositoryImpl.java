package me.liberty.ddd.repository.impl;

import me.liberty.ddd.common.dal.dao.OperateRecordDAO;
import me.liberty.ddd.common.dal.dos.OperateRecordDO;
import me.liberty.ddd.common.util.enums.OperateType;
import me.liberty.ddd.core.model.OperateRecord;
import me.liberty.ddd.repository.OperateRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-12-27 16:41
 */
@Repository
public class OperateRecordRepositoryImpl implements OperateRecordRepository {
    @Autowired
    private OperateRecordDAO operateRecordDAO;

    @Override
    public void save(OperateRecord operateRecord) {
        if (!operateRecord.isProd() || operateRecord.getOperateType() == OperateType.VIEW) {
            // 将日志投递到 oss
            // .....
        } else {
            // ... 将日志投递到数据库
            for (String message : operateRecord.getMessage()) {
                OperateRecordDO operateRecordDO = new OperateRecordDO(OperateType.ATTACH,
                        operateRecord.getOperator(), message);
                operateRecordDAO.save(operateRecordDO);
            }
        }
    }
}
