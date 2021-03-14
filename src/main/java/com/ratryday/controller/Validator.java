package com.ratryday.controller;

import com.ratryday.controller.department.Department;
import com.ratryday.controller.department.DepartmentDB;
import com.ratryday.controller.employee.Employee;
import com.ratryday.controller.employee.EmployeeDB;

import java.util.regex.Pattern;

public class Validator {

    public static boolean departmentNameValidator(String departmentName) {
        if (departmentName != null) {
            Department department = DepartmentDB.selectOne(departmentName);
            return department.getName() == null;
        }
        return false;
    }

    public static boolean employeeMailingAddressValidator(String employeeMailingAddress) {
        if (employeeMailingAddress != null) {
            Pattern mailingAddress = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
            if (mailingAddress.matcher(employeeMailingAddress).matches()) {
                Employee employee = EmployeeDB.selectOne(employeeMailingAddress);
                return employee.getMailingAddress() == null;
            }
        }
        return false;
    }
}
