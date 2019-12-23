package com.java.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.dao.EmployeeDao;
import com.java.model.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	private SessionFactory sessionFactory;

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

	public List<Employee> getEmployeeByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from Employee  where email=:email";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("email", email);
		List<Employee> user = query.list();
		t.commit();
		return user;
	}

	public List<Employee> employeeByComId(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from Employee  where com_id=:id";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("id", id);
		List<Employee> employee = query.list();
		t.commit();
		return employee;
	}

	public List<Employee> getEmployeeByIdandCom(int id, int com_id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from Employee  where id=:id and com_id=:com";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("id", id);
		query.setParameter("com", com_id);
		List<Employee> u = query.list();
		System.out.println("Person loaded successfully, Person details=" + u);
		t.commit();
		return u;
	}

	public int updateEmployeeRandomKey(String email, String randomKey) {
		System.out.println("In random key  Employee dao Impl");
		Session session = sessionFactory.getCurrentSession();// sessionFactory.openSession();
		System.out.println(email);
		System.out.println(randomKey);
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("update Employee set employeeRandom_key=:key where email=:email");
		query.setParameter("key", randomKey);
		query.setParameter("email", email);
		int result = query.executeUpdate();
		System.out.println("No of rows updated: " + result);
		t.commit();
		return result;
	}

	public List<Employee> getEmployeesByPage(int id, int pageid, int total, String search) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = " from Employee where  com_id=:id and name Like concat('%',:searchName,'%')";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("id", id);
		query.setParameter("searchName", search);
		query.setFirstResult(pageid - 1);
		query.setMaxResults(total);
		List<Employee> u = query.list();
		t.commit();
		return u;
	}

	public Long countEmployeesBySearch(int id, String search) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "select count(*) from Employee where  com_id=:id and name Like concat('%',:searchName,'%')";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("id", id);
		query.setParameter("searchName", search);
		Long count = (Long) query.uniqueResult();
		List<Employee> u = query.list();
		t.commit();
		return count;
	}

}
