package com.ratryday.dao;

import com.ratryday.models.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> select();

    Department selectOne(int id);

    Department selectOne(String departmentName);

    int insert(Department department);

    int update(Department department);

    int delete(int id);

}
