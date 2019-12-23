package com.java.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.java.dao.EmployeeRestApiDao;
import com.java.model.Employee;

@Repository
@Transactional
public class EmployeeRestApiDaoImpl implements EmployeeRestApiDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee saveEmployee(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.save(emp);
		t.commit();
		return emp;
	}

	public void updateEmployee(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.update(emp);
		t.commit();

	}

	public Employee getEmployeeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Employee u = (Employee) session.load(Employee.class, new Integer(id));
		System.out.println("Person loaded successfully, Person details=" + u);
		t.commit();
		return u;
	}

	public void removeEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Employee u = (Employee) session.load(Employee.class, new Integer(id));
		if (null != u) {
			session.delete(u);
		}
		System.out.println("Person deleted successfully, person details=" + u);
		t.commit();
	}

	public List<Employee> allEmployee() {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();

		List<Employee> allUser = session.createQuery("from Employee").list();
		t.commit();
		return allUser;
	}

	public List<Employee> getEmployeeByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from User  where employeeRandom_key=:token";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("token", token);
		List<Employee> user = query.list();
		System.out.println("Person loaded successfully, Person details=" + user);

		t.commit();
		return user;
	}

}
