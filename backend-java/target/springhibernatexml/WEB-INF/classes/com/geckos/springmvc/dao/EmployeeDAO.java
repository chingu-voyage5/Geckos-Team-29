package com.geckos.springmvc.dao;

import com.geckos.springmvc.entity.Employee;

public interface EmployeeDAO {
	public void createEmployee(Employee employee);
	public Employee getEmployee(Employee employee);

}
