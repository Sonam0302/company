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

import com.java.dao.CompanyDao;
import com.java.model.Company;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Company saveCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.save(company);
		t.commit();
		return company;
	}

	public void updateCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.update(company);
		t.commit();

	}

	public void removeCompany(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Company u = (Company) session.load(Company.class, new Integer(id));
		if (null != u) {
			session.delete(u);
		}
		System.out.println("Person deleted successfully, person details=" + u);
		t.commit();
	}

//createQuery is not valid without active transaction;
	public int updatePassword(String email, String password) {
		System.out.println("In password reset User dao");
		Session session = sessionFactory.getCurrentSession();// sessionFactory.openSession();
		System.out.println(email);
		System.out.println(password);
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("update Company set com_pass=:pass where com_email=:email");
		query.setParameter("pass", password);
		query.setParameter("email", email);
		int result = query.executeUpdate();
		// Commit the transaction and close the session
		t.commit();
		System.out.println("No of rows updated: " + result);
		return result;
	}

	public boolean findCompanyBygoogle(String google_id, String email) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from Company  where com_email=:email and google_id=:gid";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("email", email);
		query.setParameter("gid", google_id);
		List<Company> comp = query.list();
		int comp_size = comp.size();
		boolean userFound;
		if (comp_size > 0) {
			userFound = true;
		} else {
			userFound = false;
		}
		t.commit();
		return userFound;
	}

	public int updateRandomKey(String email, String randomKey) {
		System.out.println("In random key  User dao");
		Session session = sessionFactory.getCurrentSession();// sessionFactory.openSession();
		System.out.println(email);
		System.out.println(randomKey);
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("update Company set random_key=:key where com_email=:email");
		query.setParameter("key", randomKey);
		query.setParameter("email", email);
		int result = query.executeUpdate();
		// Commit the transaction and close the session
		t.commit();
		System.out.println("No of rows updated: " + result);
		return result;
	}

	public Company getCompanyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Company u = (Company) session.load(Company.class, new Integer(id));
		System.out.println("Person loaded successfully, Person details=" + u);
		t.commit();
		return u;
	}

	public List<Company> getCompanyByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from Company  where com_email=:email";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("email", email);
		List<Company> comp = query.list();
		t.commit();
		return comp;
	}

}
