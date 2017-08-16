package me.liberty.ddd.model.entity;

import lombok.Getter;
import me.liberty.ddd.model.Address;

/**
 *
 *
 * @author shouna.ysn
 * @email shouna.ysn@alipay.com
 * @version $Id: me.liberty.ddd.model.entity, v 0.1 16/08/2017
 *          Exp $
 */
public class Student extends Entity {

    @Getter
    private StudentId studentId;

    @Getter
    private String name;

    @Getter
    private Address address;

    public Student(StudentId studentId, String name, Address address) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
    }

    public static Student create(StudentId studentId, String name, Address address) {
        return new Student(studentId, name, address);
    }

}


