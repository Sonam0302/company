package com.java.service;

import java.util.List;

import com.java.model.Employee;

public interface EmployeeRestApiService {

	public Employee saveEmployee(Employee emp);

	public void updateEmployee(Employee emp);

	public Employee getEmployeeById(int id);

	public void removeEmployee(int id);

	public List<Employee> allEmployee();

	public List<Employee> getEmployeeByToken(String token);

}
