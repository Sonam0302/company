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

import com.java.dao.CompanyRestApiDao;
import com.java.model.Company;

@Repository
@Transactional
public class CompanyRestApiDaoImpl implements CompanyRestApiDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Company> getCompanyByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from Company  where random_key=:token";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("token", token);
		List<Company> com = query.list();
		t.commit();
		System.out.println("Person loaded successfully, Person details=" + com);
		return com;
	}

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

	public Company getCompanyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Company u = (Company) session.load(Company.class, new Integer(id));
		System.out.println("Person loaded successfully, Person details=" + u);
		t.commit();
		return u;
	}

	public List<Company> getCompany() {
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Company> com = session.createQuery("from Company").list();
		t.commit();
		return com;
	}

}
