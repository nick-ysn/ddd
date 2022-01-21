package me.liberty.ddd.common.dal.dos;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.OperateType;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-12-27 16:34
 */
@Entity
@Table(name = "operate_record")
public class OperateRecordDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "operate_type")
    private OperateType operateType;

    @Column
    @Getter
    private  String operator;

    @Column(name = "message")
    @Getter
    private String message;

    @Column
    @Getter
    private Date gmtCreated;

    @Column
    @Getter
    private Date gmtModified;

    public OperateRecordDO() {
    }

    public OperateRecordDO(OperateType operateType, String operator, String message) {
        this.operateType = operateType;
        this.operator = operator;
        this.message = message;
    }
}
