package me.liberty.ddd.biz.service.perm.impl;

import me.liberty.ddd.common.adapter.OssAdapter;
import me.liberty.ddd.common.dal.dao.OperateRecordDAO;
import me.liberty.ddd.common.dal.dos.OperateRecordDO;
import me.liberty.ddd.common.util.enums.OperateType;
import me.liberty.ddd.core.model.OperateRecord;
import me.liberty.ddd.repository.OperateRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-12-27 16:32
 */
public class OperateRecordDemoServiceImpl {

    @Autowired
    private OperateRecordDAO operateRecordDAO;
    @Autowired
    private OssAdapter ossAdapter;
    @Autowired
    private OperateRecordRepository operateRecordRepository;

    public void attachDemo() {

        String operator = "liberty";
        List<String> messages = new ArrayList<>();
        // 1. 执行一堆  attach 操作
        messages.add("第一步....");
        messages.add("第二步....");
        // 2. 添加日志
        for (String message : messages) {
            OperateRecordDO operateRecordDO = new OperateRecordDO(OperateType.ATTACH, operator, message);
            operateRecordDAO.save(operateRecordDO);
        }

    }

    public void attachDemoV2(String env) {

        String operator = "liberty";
        List<String> messages = new ArrayList<>();
        // 1. 执行一堆  attach 操作
        messages.add("第一步....");
        messages.add("第二步....");
        // 2. 添加日志
        if (!env.equalsIgnoreCase("PROD")) {
            // 将日志投递到 oss
            // .....
        } else {
            // ... 将日志投递到数据库
            for (String message : messages) {
                OperateRecordDO operateRecordDO = new OperateRecordDO(OperateType.ATTACH, operator, message);
                operateRecordDAO.save(operateRecordDO);
            }
        }


    }

    public void createDemo(OperateRecord operateRecord) {
        String operator = "liberty";
        List<String> messages = new ArrayList<>();
        // 1. 执行一堆  attach 操作
        messages.add("第一步....");
        messages.add("第二步....");
        // 2. 添加日志
        for (String message : messages) {
            OperateRecordDO operateRecordDO = new OperateRecordDO(OperateType.CREATE, operator, message);
            operateRecordDAO.save(operateRecordDO);
        }
    }

    public void saveDemo() {
        String operator = "liberty";
        List<String> messages = new ArrayList<>();
        List<OperateRecord> operateRecords = new ArrayList<>();
        // 1. 执行一堆  attach 操作
        messages.add("第一步....");
        messages.add("第二步....");

        // ... 执行 Create
        operateRecords.add(new OperateRecord(OperateType.VIEW, messages, operator));

        // ... 执行 Attach
        operateRecords.add(new OperateRecord(OperateType.ATTACH, messages, operator));

        operateRecords.stream().forEach(operateRecord -> {
            operateRecordRepository.save(operateRecord);
        });
    }


}
