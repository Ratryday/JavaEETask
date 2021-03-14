package com.ratryday.controller.employee;

import java.io.Serializable;
import java.sql.Date;


public class Employee implements Serializable {

    private static final long serialVersionUID = 4059388415027009547L;

    private int idEmployee;
    private String employeeName;
    private Date hiringDate;
    private int experience;
    private String mailingAddress;
    private int departmentID;

    public Employee() {
    }

    public Employee(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Employee(String employeeName, Date hiringDate, int experience, String mailingAddress, int departmentID) {
        this.employeeName = employeeName;
        this.hiringDate = hiringDate;
        this.experience = experience;
        this.mailingAddress = mailingAddress;
        this.departmentID = departmentID;
    }

    public Employee(int idEmployee, String employeeName, Date hiringDate, int experience, String mailingAddress, int departmentID) {
        this.idEmployee = idEmployee;
        this.employeeName = employeeName;
        this.hiringDate = hiringDate;
        this.experience = experience;
        this.mailingAddress = mailingAddress;
        this.departmentID = departmentID;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
