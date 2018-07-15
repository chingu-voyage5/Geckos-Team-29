package com.geckos.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.geckos.springmvc.auth.LoginCredentials;
import com.geckos.springmvc.dao.EmployeeDAO;
import com.geckos.springmvc.entity.Employee;
import com.geckos.springmvc.service.EmployeeService;

@Transactional
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService{

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public void createEmployee(Employee employee) {

		employeeDAO.createEmployee(employee);

	}

	@Override
	public Employee getEmployee(LoginCredentials loginCredentials) {

		return employeeDAO.getEmployee(loginCredentials);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}

}
