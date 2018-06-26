package com.geckos.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geckos.springmvc.dao.EmployeeDAO;
import com.geckos.springmvc.entity.Employee;
import com.geckos.springmvc.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public void createEmployee(Employee employee) {

		employeeDAO.createEmployee(employee);

	}

	@Override
	public Employee getEmployee(Employee employee) {

		return employeeDAO.getEmployee(employee);
	}

}
