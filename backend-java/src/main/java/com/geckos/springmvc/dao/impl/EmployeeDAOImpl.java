package com.geckos.springmvc.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.geckos.springmvc.dao.EmployeeDAO;
import com.geckos.springmvc.entity.Employee;

@Repository
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
	public Employee getEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		Criteria criteria = null;
		Employee emp = null;
		try {
			criteria = session.createCriteria(Employee.class);
			Criterion criterion = Restrictions.eq("email",
					employee.getEmail());
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