package com.geckos.springmvc.service;

import com.geckos.springmvc.auth.LoginCredentials;
import com.geckos.springmvc.entity.Employee;

public interface EmployeeService {
	public void createEmployee(Employee employee);
	public Employee getEmployee(LoginCredentials employee);
	public Employee getEmployeeByEmail(String email);
}
