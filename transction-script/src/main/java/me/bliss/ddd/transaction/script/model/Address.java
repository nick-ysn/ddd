package me.bliss.ddd.transaction.script.model;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.model, v 0.1 3/10/16
 *          Exp $
 */
public class Address {

    private String country;

    private String city;

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
