package com.ratryday.models;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = -6112754067058666395L;

    private int id;
    private String name;

    public Department() {
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

    public static class DepartmentBuilder {

        private int id;
        private String name;

        public DepartmentBuilder setId(int id) {
            this.id = id;

            return this;
        }

        public DepartmentBuilder setName(String name) {
            this.name = name;

            return this;
        }

        public Department build(){
            Department department = new Department();

            department.id = this.id;
            department.name = this.name;

            return department;
        }
    }
}
