package me.liberty.ddd.core.model;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.OperateType;

import java.util.List;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-12-27 16:18
 */
public class OperateRecord {

    @Getter
    private String recordId;
    @Getter
    private OperateType operateType;
    @Getter
    private List<String> message;
    @Getter
    private String operator;
    @Getter
    private String env;

    public OperateRecord(OperateType operateType, List<String> message, String operator) {
        this.operateType = operateType;
        this.message = message;
        this.operator = operator;
    }

    public boolean isProd() {
        return "PROD".equalsIgnoreCase(env);
    }
}
