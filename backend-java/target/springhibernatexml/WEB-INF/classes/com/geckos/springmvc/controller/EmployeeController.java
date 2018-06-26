package com.geckos.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geckos.springmvc.entity.Employee;
import com.geckos.springmvc.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Object createUser(@RequestBody Employee employee) {
		Map<String, String> errorMap = new HashMap<String, String>();
		if (employee.getEmail() == null || employee.getName() == null || employee.getPassword() == null
				|| employee.getSurname() == null) {
			if (employee.getEmail() == null) {
				errorMap.put("email", "-1");
			}

			if (employee.getName() == null) {
				errorMap.put("name", "-1");
			}

			if (employee.getPassword() == null) {
				errorMap.put("password", "-1");
			}

			if (employee.getSurname() == null) {
				errorMap.put("surname", "-1");
			}

			return errorMap;
		}
		employeeService.createEmployee(employee);
		return "employee saved";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Object processLogin(@RequestBody Employee employee) {
		Map<String, String> errorMap = new HashMap<String, String>();

		if (employee.getEmail() == null || employee.getPassword() == null) {
			if (employee.getEmail() == null) {
				errorMap.put("email", "-1");
			}

			if (employee.getPassword() == null) {
				errorMap.put("password", "-1");
			}
			return errorMap;
		}

		Employee emp = employeeService.getEmployee(employee);
		// return JWT
		return emp;

	}

}