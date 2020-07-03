package me.liberty.ddd.transaction.script.dos;

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

    private String userType;

    private String status;

    public StudentDO(int id, String name, int age, String userType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userType = userType;
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

    public String getUserType() {
        return userType;
    }

    public String getStatus() {
        return status;
    }
}
