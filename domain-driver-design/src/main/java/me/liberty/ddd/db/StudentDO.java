package me.liberty.ddd.db;

import lombok.Getter;
import lombok.Setter;

/**
 *
 *
 * @author shouna.ysn
 * @email shouna.ysn@alipay.com
 * @version $Id: me.liberty.ddd.db, v 0.1 16/08/2017
 *          Exp $
 */
public class StudentDO {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String addressId;

    public StudentDO(String name) {
        this.name = name;
    }
}
