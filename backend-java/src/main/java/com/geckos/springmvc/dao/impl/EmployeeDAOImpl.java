package com.geckos.springmvc.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.geckos.springmvc.auth.LoginCredentials;
import com.geckos.springmvc.dao.EmployeeDAO;
import com.geckos.springmvc.entity.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void createEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
		session.save(employee);
		session.close();
	}

	@Override
	public Employee getEmployee(LoginCredentials loginCredentials) {
		Session session = sessionFactory.openSession();
		Criteria criteria = null;
		Employee emp = null;
		try {
			criteria = session.createCriteria(Employee.class);
			Criterion criterion = Restrictions.eq("email",
					loginCredentials.getEmail());
			criteria.add(criterion);

			criteria.setMaxResults(1);
			emp = (Employee) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println(emp.getCompanyId());
		System.out.println(emp.getEmail());
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		System.out.println(emp.getPassword());
		System.out.println(emp.getSurname());
		return emp;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		Session session = sessionFactory.openSession();
		Criteria criteria = null;
		Employee emp = null;
		try {
			criteria = session.createCriteria(Employee.class);
			Criterion criterion = Restrictions.eq("email",email);
			criteria.add(criterion);
			criteria.setMaxResults(1);
			emp = (Employee) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return emp;
	}
	}

