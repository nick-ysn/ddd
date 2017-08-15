package me.bliss.ddd.transaction.script.dos;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.dos, v 0.1 3/10/16
 *          Exp $
 */
public class StudentDO {

    private int id;

    private String name;

    private int age;

    public StudentDO(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
