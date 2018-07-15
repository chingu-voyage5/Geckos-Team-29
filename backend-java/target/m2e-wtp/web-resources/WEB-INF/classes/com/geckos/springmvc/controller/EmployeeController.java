package com.geckos.springmvc.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geckos.springmvc.auth.LoginCredentials;
import com.geckos.springmvc.entity.Employee;
import com.geckos.springmvc.service.EmployeeService;
import com.geckos.springmvc.service.JwtService;
import com.geckos.springmvc.service.LoginService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private JwtService jwtService;

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
	public Object processLogin(@RequestBody LoginCredentials loginCredentials,HttpServletResponse response) {
		Map<String, String> errorMap = new HashMap<String, String>();

		if (loginCredentials.getEmail() == null || loginCredentials.getPassword() == null) {
			if (loginCredentials.getEmail() == null) {
				errorMap.put("email", "-1");
			}

			if (loginCredentials.getPassword() == null) {
				errorMap.put("password", "-1");
			}
			return errorMap;
		}
		
		if(loginService.login(loginCredentials)) {
			Employee emp = employeeService.getEmployee(loginCredentials);
			try {
				response.setHeader("Token", jwtService.tokenFor(emp));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
			return emp;
		}
		errorMap.put("credentials", "wrong email or password");
		return errorMap;

	}
	
}