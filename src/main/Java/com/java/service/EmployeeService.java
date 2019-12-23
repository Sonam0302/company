package com.java.service;

import java.util.List;

import com.java.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee emp);

	public void updateEmployee(Employee emp);

	public Employee getEmployeeById(int id);

	public void removeEmployee(int id);

	public List<Employee> getEmployeeByEmail(String email);

	public List<Employee> employeeByComId(int id);

	public List<Employee> getEmployeeByIdandCom(int id, int com_id);

	public int updateEmployeeRandomKey(String email, String randomKey);

	public List<Employee> getEmployeesByPage(int id, int pageid, int total, String search); // for pagination and search

	public Long countEmployeesBySearch(int id, String search);

}
