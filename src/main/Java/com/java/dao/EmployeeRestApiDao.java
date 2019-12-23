package com.java.dao;

import java.util.List;

import com.java.model.Employee;

public interface EmployeeRestApiDao {

	public Employee saveEmployee(Employee emp);

	public void updateEmployee(Employee emp);

	public Employee getEmployeeById(int id);

	public void removeEmployee(int id);

	public List<Employee> allEmployee();

	public List<Employee> getEmployeeByToken(String token);

}
