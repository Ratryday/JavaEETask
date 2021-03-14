package com.ratryday.controller;

import com.ratryday.controller.department.Department;
import com.ratryday.controller.department.DepartmentDB;
import com.ratryday.controller.employee.EmployeeDB;
import com.ratryday.controller.employee.Employee;


import java.sql.Date;
import java.util.regex.Pattern;

public class Validator {

    public static boolean departmentNameValidator(String departmentName) {
        if (departmentName != null) {
            if (departmentName == ""){
                throw new NullPointerException("У департамента должно быть имя");
            }
            Department department = DepartmentDB.selectOne(departmentName);
            return department == null;
        }
        return false;
    }

    public static int[] employeeMailingAddressValidator(String employeeName, Date employeeHiringDate, Integer employeeExperience, String employeeMailingAddress) {
        int[] employeeValidator = {1,1,1,1};
        if (employeeName != null) {
            if (employeeName == "") {
                employeeValidator[1] = 0;
            }
        } else {
            employeeValidator[1] = 0;
        }

        if (employeeHiringDate == null) {
            employeeValidator[2] = 0;
        }

        if (employeeExperience == null) {
            employeeValidator[3] = 0;
        }

        if (employeeMailingAddress != null) {
            if (employeeMailingAddress == "") {
                employeeValidator[4] = 0;
            }
            Pattern mailingAddress = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
            if (mailingAddress.matcher(employeeMailingAddress).matches()) {
                Employee employee = EmployeeDB.selectOne(employeeMailingAddress);
                if (employee == null) {
                    employeeValidator[4] = 1;
                } else {
                    employeeValidator[4] = -1;
                }
            }
        }

        return employeeValidator;
    }
}
