package me.bliss.ddd.transaction.script.model;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.model, v 0.1 2/26/16
 *          Exp $
 */
public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
