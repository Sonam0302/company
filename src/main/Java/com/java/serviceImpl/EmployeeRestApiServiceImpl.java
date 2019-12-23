package com.java.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.EmployeeRestApiDao;
import com.java.model.Employee;
import com.java.service.EmployeeRestApiService;

@Service
public class EmployeeRestApiServiceImpl implements EmployeeRestApiService {

	@Autowired
	EmployeeRestApiDao emprestdao;

	public Employee saveEmployee(Employee emp) {

		return emprestdao.saveEmployee(emp);
	}

	public void updateEmployee(Employee emp) {
		emprestdao.updateEmployee(emp);

	}

	public Employee getEmployeeById(int id) {

		return emprestdao.getEmployeeById(id);
	}

	public void removeEmployee(int id) {
		emprestdao.removeEmployee(id);

	}

	public List<Employee> allEmployee() {
		return emprestdao.allEmployee();
	}

	public List<Employee> getEmployeeByToken(String token) {
		return emprestdao.getEmployeeByToken(token);
	}
}
