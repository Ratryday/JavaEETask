package com.ratryday.controllers;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.dao.DepartmentDB;
import com.ratryday.dao.EmployeeDB;

import java.util.regex.Pattern;
import java.time.LocalDate;

import static com.ratryday.controllers.Constants.MAILING_ADDRESS_PATTERN;

public class Validator {

    private DepartmentDB departmentDB;
    private EmployeeDB employeeDB;

    public boolean isDepartmentNameValid(String departmentName) {
        if (StringUtils.isEmpty(departmentName)) {
            return false;
        } else {
            departmentDB.selectOne(departmentName);
            return departmentDB == null;
        }
    }

    public boolean isValid(String employeeName, LocalDate employeeHiringDate, Integer employeeExperience, String employeeMailingAddress) {
        boolean isValid = true;

        if (StringUtils.isEmpty(employeeName)) {
            isValid = false;
        }

        if (employeeHiringDate == null) {
            isValid = false;
        }

        if (employeeExperience == null) {
            return false;
        } else {
            if (employeeExperience <= 0) {
                isValid = false;
            }
        }

        if (StringUtils.isEmpty(employeeMailingAddress)) {
            isValid = false;
        } else {
            Pattern mailingAddress = Pattern.compile(MAILING_ADDRESS_PATTERN);
            if (mailingAddress.matcher(employeeMailingAddress).matches()) {
                employeeDB.selectOne(employeeMailingAddress);
                if (employeeDB != null) {
                    isValid = false;
                }
            } else {
                isValid = false;
            }
        }
        return isValid;
    }


}
