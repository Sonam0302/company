package com.java.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Employee;
import com.java.service.EmployeeRestApiService;

@RestController
public class EmployeeRestApiController {

	@Autowired
	EmployeeRestApiService emprestservice;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// geting employee by random_key
	@GetMapping("/employee_token")
	public Map<String, Object> getEmployeeByToken(
			@RequestHeader(value = "authorization", required = false) String token) {
		System.out.println(token);
		/*
		 * if (token == null) { Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("status", "401"); map.put("error", "Unauthorized Access");
		 * System.out.print(map); return map; }
		 */
		if (token != null) {
			boolean isValid = token
					.matches("([0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12})");
			System.out.print("checking string is valid uuid" + isValid);

			if (isValid == true) {
				List<Employee> user = emprestservice.getEmployeeByToken(token);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("date", user);
				return map;
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("status", "401");
				map.put("error", "Invalid credentials");
				System.out.print(map);
				return map;
			}

		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "401");
			map.put("error", "Unauthorized Access");
			System.out.print(map);
			return map;

		}
	}

	// getting all the employee which is registred
	@GetMapping(value = "/employee")
	public List<Employee> getEmployee() {
		return emprestservice.allEmployee();
	}

	// geeting employee for particukar id
	@GetMapping(value = "/employee/{id}")
	public Employee getEmployeeById(@PathVariable Integer id) {
		return emprestservice.getEmployeeById(id);
	}

	// save employee
	@PostMapping(value = "/empolyee/save")
	public Employee createEmp(@RequestBody Employee user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return emprestservice.saveEmployee(user);
	}

	// delete employee of particular id
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		emprestservice.removeEmployee(id);
	}

	// get employee which are registered in a particular company
	/*
	 * @GetMapping("/employee/company/{id}") public List<User>
	 * getEmployeeByComId(@PathVariable Integer id) { return
	 * service.employeeByComId(id); }
	 */
	// updating emloyee

	@PutMapping("/employee/{id}")
	public void updateEmployee(@PathVariable Integer id, @RequestBody Employee user) {
		Employee em = emprestservice.getEmployeeById(id);
		em.setName(user.getName());
		em.setAddress(user.getAddress());
		em.setCity(user.getCity());
		em.setState(user.getState());
		emprestservice.updateEmployee(em);
	}

}
