package com.ratryday.controller.department;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = -6112754067058666395L;

    private int id;
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
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

}
