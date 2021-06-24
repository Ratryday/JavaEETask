package com.ratryday.services;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.controllers.Validator;
import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.dao.EmployeeDaoImpl;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.dao.EmployeeDao;
import com.ratryday.models.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ratryday.controllers.Constants.*;

public class DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private Validator validator = new Validator();
    private String name;

    public boolean isCreate(HttpServletRequest httpServletRequest) {
        // Get request parameters
        if (StringUtils.isEmpty(httpServletRequest.getParameter(NAME))) {
            name = httpServletRequest.getParameter(NAME);
        }

        // Check on validity
        if (validator.isDepartmentNameValid(name)) {
            // Department Builder
            Department department = new Department.DepartmentBuilder().setName(name).build();

            // Do DB command
            departmentDao.insert(department);

            return true;
        } else {
            // Set attribute
            httpServletRequest.setAttribute(NAME, name);

            return false;
        }
    }

    public void prepareForUpdate(HttpServletRequest httpServletRequest) {
        // Get request parameters
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));

        // Do DB command
        departmentDao.selectOne(id);

        // Set attribute
        httpServletRequest.setAttribute(DEPARTMENTS, departmentDao);
    }

    public boolean isUpdate(HttpServletRequest httpServletRequest) {
        // Get request parameters
        if (!StringUtils.isEmpty(httpServletRequest.getParameter(NAME))) {
            name = httpServletRequest.getParameter(NAME);
        }
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));

        // Check on validity
        if (validator.isDepartmentNameValid(name)) {
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(name)
                    .build();

            // Do DB command
            departmentDao.update(department);

            return true;
        } else {
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(name)
                    .build();

            // Set attribute
            httpServletRequest.setAttribute(DEPARTMENT, department);

            return false;
        }
    }

    public void delete(HttpServletRequest httpServletRequest) {
        // Get request parameters
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        List<Employee> employeeList = employeeDao.select(id);

        // Do DB command
        for (Employee emp : employeeList) {
            // At first deleting employees
            employeeDao.delete(emp.getIdEmployee(), id);
        }
        departmentDao.delete(id);
    }

    public List<Department> getList(HttpServletRequest httpServletRequest) {
        // Do DB command
        List<Department> departmentList = departmentDao.select();

        // Set attribute
        httpServletRequest.setAttribute(DEPARTMENTS, departmentList);

        return departmentList;
    }

}
