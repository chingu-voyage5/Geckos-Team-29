package com.geckos.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.geckos.springmvc.auth.LoginCredentials;
import com.geckos.springmvc.entity.Employee;

@Component
public class LoginService {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public boolean login(LoginCredentials credentials) {
		//Employee employee = employeeService.getEmployeeByEmail(credentials.getEmail());
		Employee employee = employeeService.getEmployee(credentials);
		System.out.println(credentials.getEmail());
		System.out.println(credentials.getPassword());
		System.out.println(employee.getPassword());
		System.out.println(bCryptPasswordEncoder.matches(credentials.getPassword(), employee.getPassword()));
		return bCryptPasswordEncoder.matches(credentials.getPassword(), employee.getPassword());
	}
}