package com.java.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CompanyDao;
import com.java.model.Company;
import com.java.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao comdao;

	public Company saveCompany(Company company) {
		return comdao.saveCompany(company);
	}

	public void updateCompany(Company company) {
		comdao.updateCompany(company);

	}

	public void removeCompany(int id) {
		comdao.removeCompany(id);

	}

	public int updatePassword(String email, String password) {
		return comdao.updatePassword(email, password);
	}

	public boolean findCompanyBygoogle(String google_id, String email) {
		return comdao.findCompanyBygoogle(google_id, email);
	}

	public int updateRandomKey(String email, String randomKey) {
		return comdao.updateRandomKey(email, randomKey);
	}

	public Company getCompanyById(int id) {
		return comdao.getCompanyById(id);
	}

	public List<Company> getCompanyByEmail(String email) {
		return comdao.getCompanyByEmail(email);
	}

}
