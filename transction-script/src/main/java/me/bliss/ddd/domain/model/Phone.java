package me.bliss.ddd.domain.model;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.domain.model, v 0.1 3/10/16
 *          Exp $
 */
public class Phone {

    private String number;

    private String desc;

    public Phone(String number, String desc) {
        this.number = number;
        this.desc = desc;
    }

    public String getNumber() {
        return number;
    }

    public String getDesc() {
        return desc;
    }
}
