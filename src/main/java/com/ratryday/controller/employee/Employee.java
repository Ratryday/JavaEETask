package com.ratryday.controller.employee;

import javax.xml.crypto.Data;
import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 4059388415027009547L;

    private int id;
    private String name;
    private Data birthday;
    private int experience;
    private String mailingAddress;

    Employee() {
    }

    Employee(String name, Data birthday, int experience, String mailingAddress) {
        this.name = name;
        this.birthday = birthday;
        this.experience = experience;
        this.mailingAddress = mailingAddress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getBirthday() {
        return birthday;
    }

    public void setBirthday(Data birthday) {
        this.birthday = birthday;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

}
