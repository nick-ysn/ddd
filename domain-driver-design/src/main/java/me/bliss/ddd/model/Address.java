package me.bliss.ddd.model;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.model, v 0.1 4/8/16
 *          Exp $
 */
public class Address {

    @Getter
    private String country;

    @Getter
    private String province;

    @Getter
    private String city;

    public Address(String country,String province,String city){
        this.setCountry(country);
        this.setProvince(province);
        this.setCity(city);
    }

    public String generateSimpleAddress(){
        return country + "-" + province + "-" + city;
    }

    private void setCountry(String country) {
        if (StringUtils.isBlank(country)) {
            throw new IllegalArgumentException("country isn't allowed blank");
        }
        this.country = country;
    }

    private void setProvince(String province) {
        if (StringUtils.isBlank(province)){
            throw new IllegalArgumentException("province isn't allowed blank");
        }
        this.province = province;
    }

    private void setCity(String city) {
        if (StringUtils.isBlank(city)){
            throw new IllegalArgumentException("city isn't allowed blank");
        }
        this.city = city;
    }
}
