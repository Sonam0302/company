package com.java.dao;

import java.util.List;

import com.java.model.Company;

public interface CompanyRestApiDao {

	public List<Company> getCompanyByToken(String token);

	public Company saveCompany(Company company);

	public void updateCompany(Company company);

	public void removeCompany(int id);

	public Company getCompanyById(int id);

	public List<Company> getCompany();

}
