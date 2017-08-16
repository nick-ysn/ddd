package me.liberty.ddd.model.entity;

import lombok.Getter;

/**
 *
 *
 * @author shouna.ysn
 * @email shouna.ysn@alipay.com
 * @version $Id: me.liberty.ddd.model.entity, v 0.1 16/08/2017
 *          Exp $
 */
public class StudentId {

    @Getter
    private int id;

    public StudentId(int id) {
        this.id = id;
    }
}
