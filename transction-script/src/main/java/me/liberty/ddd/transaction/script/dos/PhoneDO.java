package me.liberty.ddd.transaction.script.dos;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.dos, v 0.1 3/10/16
 *          Exp $
 */
public class PhoneDO {

    private int id;

    private String number;

    private String desc;

    public PhoneDO(int id, String number,String desc) {
        this.id = id;
        this.number = number;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
