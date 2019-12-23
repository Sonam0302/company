package com.java.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.EmployeeDao;
import com.java.model.Employee;
import com.java.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao empdao;

	public Employee saveEmployee(Employee emp) {
		return empdao.saveEmployee(emp);
	}

	public void updateEmployee(Employee emp) {
		empdao.updateEmployee(emp);

	}

	public Employee getEmployeeById(int id) {
		return empdao.getEmployeeById(id);
	}

	public void removeEmployee(int id) {
		empdao.removeEmployee(id);

	}

	public List<Employee> getEmployeeByEmail(String email) {
		return empdao.getEmployeeByEmail(email);
	}

	public List<Employee> employeeByComId(int id) {
		return empdao.employeeByComId(id);
	}

	public List<Employee> getEmployeeByIdandCom(int id, int com_id) {
		return empdao.getEmployeeByIdandCom(id, com_id);
	}

	public int updateEmployeeRandomKey(String email, String randomKey) {
		return empdao.updateEmployeeRandomKey(email, randomKey);
	}

	public List<Employee> getEmployeesByPage(int id, int pageid, int total, String search) {
		return empdao.getEmployeesByPage(id, pageid, total, search);
	}

	public Long countEmployeesBySearch(int id, String search) {
		return empdao.countEmployeesBySearch(id, search);
	}

}
