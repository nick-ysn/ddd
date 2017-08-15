package me.bliss.ddd.transaction.script.dos;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.dos, v 0.1 3/11/16
 *          Exp $
 */
public class CustomerDO {

    private String name;

    private String phoneNo;

    private String country;

    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
