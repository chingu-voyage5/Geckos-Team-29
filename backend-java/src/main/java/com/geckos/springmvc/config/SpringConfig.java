package com.geckos.springmvc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.geckos.springmvc.dao.EmployeeDAO;
import com.geckos.springmvc.dao.impl.EmployeeDAOImpl;
import com.geckos.springmvc.service.EmployeeService;
import com.geckos.springmvc.service.impl.EmployeeServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.geckos.springmvc.controller")  
public class SpringConfig implements WebMvcConfigurer {
	 //SpringConfig class is the source of Spring beans
	@Bean
	public EmployeeService employeeService() {
		System.out.println("Create bean employeeService");
		return new EmployeeServiceImpl();
	}
	
	@Bean
	public EmployeeDAO employeeDao() {
		System.out.println("Create bean employeeDao");
		return new EmployeeDAOImpl();
	}
}
