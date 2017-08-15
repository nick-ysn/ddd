package me.liberty.ddd.transaction.script.dos;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.dos, v 0.1 3/9/16
 *          Exp $
 */
public class User {

    private int id;

    private String name;

    private int age;

    public User(int id,String name,int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String name,int age){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
