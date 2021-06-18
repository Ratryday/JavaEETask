package com.ratryday.models;

import java.io.Serializable;
import java.time.LocalDate;


public class Employee implements Serializable {

    private static final long serialVersionUID = 4059388415027009547L;

    private int idEmployee;
    private int departmentID;
    private Integer experience;
    private String employeeName;
    private LocalDate hiringDate;
    private String mailingAddress;

    public Employee() {
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

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
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

    public static class EmployeeBuilder {

        private int idEmployee;
        private int departmentID;
        private Integer experience;
        private String employeeName;
        private LocalDate hiringDate;
        private String mailingAddress;

        public EmployeeBuilder setIdEmployee(int idEmployee) {
            this.idEmployee = idEmployee;

            return this;
        }

        public EmployeeBuilder setEmployeeName(String employeeName) {
            this.employeeName = employeeName;

            return this;
        }

        public EmployeeBuilder setHiringDate(LocalDate hiringDate) {
            this.hiringDate = hiringDate;

            return this;
        }

        public EmployeeBuilder setExperience(Integer experience) {
            this.experience = experience;

            return this;
        }

        public EmployeeBuilder setMailingAddress(String mailingAddress) {
            this.mailingAddress = mailingAddress;

            return this;
        }

        public EmployeeBuilder setDepartmentID(int departmentID) {
            this.departmentID = departmentID;

            return this;
        }

        public Employee build() {
            Employee employee = new Employee();

            employee.idEmployee = this.idEmployee;
            employee.hiringDate = this.hiringDate;
            employee.experience = this.experience;
            employee.departmentID = this.departmentID;
            employee.employeeName = this.employeeName;
            employee.mailingAddress = this.mailingAddress;

            return employee;
        }
    }
}
