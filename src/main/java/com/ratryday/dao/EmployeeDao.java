package com.ratryday.dao;

import com.ratryday.models.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> select(int id);

    Employee selectOne(int id);

    Employee selectOne(String mail);

    int insert(Employee employee);

    int update(Employee employee);

    int delete(int idEmployee, int departmentID);

}
