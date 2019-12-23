package com.java.service;

import java.util.List;

import com.java.model.Company;

public interface CompanyService {

	public Company saveCompany(Company company);

	public void updateCompany(Company company);

	public void removeCompany(int id);

	public int updatePassword(String email, String password);

	public boolean findCompanyBygoogle(String google_id, String email);

	public int updateRandomKey(String email, String randomKey);

	public Company getCompanyById(int id);

	public List<Company> getCompanyByEmail(String email);

	// for rest Api

	public List<Company> getCompanyByToken(String token);

	public List<Company> getCompany();

}
