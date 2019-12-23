package com.java.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CompanyRestApiDao;
import com.java.model.Company;
import com.java.service.CompanyRestApiService;

@Service
public class CompanyRestApiServiceImpl implements CompanyRestApiService {
	@Autowired
	private CompanyRestApiDao ComRestDao;

	public List<Company> getCompanyByToken(String token) {
		return ComRestDao.getCompanyByToken(token);
	}

	public Company saveCompany(Company company) {

		return ComRestDao.saveCompany(company);
	}

	public void updateCompany(Company company) {
		ComRestDao.updateCompany(company);

	}

	public void removeCompany(int id) {
		ComRestDao.removeCompany(id);

	}

	public Company getCompanyById(int id) {
		return ComRestDao.getCompanyById(id);
	}

	public List<Company> getCompany() {

		return ComRestDao.getCompany();
	}
}
