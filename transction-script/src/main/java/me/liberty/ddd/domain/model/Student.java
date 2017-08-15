package me.liberty.ddd.domain.model;

import java.util.List;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.domain.model, v 0.1 3/10/16
 *          Exp $
 */
public class Student {

    private StudentId studentId;

    private String name;

    private int age;

    private List<Phone> phones;

    public void changePhone(Phone changePhone) {
        for (Phone phone : phones) {
            if (phone.getNumber().equals(changePhone.getNumber())) {
                phones.remove(phone);
            }
        }
        phones.add(changePhone);
    }
}
