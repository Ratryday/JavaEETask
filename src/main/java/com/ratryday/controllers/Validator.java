package com.ratryday.controllers;

import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;
import com.ratryday.dao.EmployeeDB;
import com.ratryday.models.Employee;


import java.sql.Date;
import java.util.regex.Pattern;

public class Validator {

    public static boolean departmentNameValidator(String departmentName) {
        if (departmentName != null) {
            if (departmentName == "") {
                throw new NullPointerException("У департамента должно быть имя");
            }
            Department department = DepartmentDB.selectOne(departmentName);
            return department == null;
        }
        return false;
    }

    public static boolean isValidator(String employeeName, Date employeeHiringDate, Integer employeeExperience, String employeeMailingAddress) {
        boolean isValid = true;
        if (employeeName != null) {
            if (employeeName == "") {
                isValid = false;
            }
        } else {
            isValid = false;
        }

        if (employeeHiringDate == null) {
            isValid = false;
        }

        if (employeeExperience == null) {
            if (employeeExperience >= 0) {
                isValid = false;
            }
        }

        if (employeeMailingAddress != null) {
            if (employeeMailingAddress == "") {
                isValid = false;
            } else {
                Pattern mailingAddress = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
                if (mailingAddress.matcher(employeeMailingAddress).matches()) {
                    Employee employee = EmployeeDB.selectOne(employeeMailingAddress);
                    if (employee != null) {
                        isValid = false;
                    }
                } else {
                    isValid = false;
                }
            }
        } else {
            isValid = false;
        }
        return isValid;
    }
}
