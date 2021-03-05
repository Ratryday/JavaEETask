package com.ratryday.controller.employee;

import java.io.Serializable;
import java.util.Date;


public class Employee implements Serializable {

    private static final long serialVersionUID = 4059388415027009547L;

    private int idEmployee;
    private String name;
    private Date hiringDate;
    private int experience;
    private String mailingAddress;
    private int departmentID;

    Employee() {
    }

    Employee(String name, Date hiringDate, int experience, String mailingAddress, int departmentID) {
        this.name = name;
        this.hiringDate = hiringDate;
        this.experience = experience;
        this.mailingAddress = mailingAddress;
        this.departmentID = departmentID;
    }

    Employee(int idEmployee, String name, Date hiringDate, int experience, String mailingAddress, int departmentID) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.hiringDate = hiringDate;
        this.experience = experience;
        this.mailingAddress = mailingAddress;
        this.departmentID = departmentID;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
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

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
}
