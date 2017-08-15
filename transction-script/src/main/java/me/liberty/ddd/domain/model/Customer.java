package me.liberty.ddd.domain.model;

import me.liberty.ddd.transaction.script.model.Address;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.model, v 0.1 3/11/16
 *          Exp $
 */
public class Customer {

    private String name;

    private String phoneNo;

    private Address address;

    public void changeAddress(Address address){
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
