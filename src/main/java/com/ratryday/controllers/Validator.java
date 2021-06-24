package com.ratryday.controllers;

import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.dao.EmployeeDaoImpl;
import org.apache.commons.lang3.StringUtils;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.dao.EmployeeDao;

import java.util.regex.Pattern;
import java.time.LocalDate;

import static com.ratryday.controllers.Constants.MAILING_ADDRESS_PATTERN;

public class Validator {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    public boolean isDepartmentNameValid(String departmentName) {
        if (StringUtils.isEmpty(departmentName)) {
            return false;
        } else {
            return departmentDao.selectOne(departmentName).getName() == null;
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
                if (employeeDao.selectOne(employeeMailingAddress).getMailingAddress() != null) {
                    isValid = false;
                }
            } else {
                isValid = false;
            }
        }
        return isValid;
    }


}
