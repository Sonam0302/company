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

import com.java.model.Company;
import com.java.service.CompanyRestApiService;

@RestController
public class CompanyRestApiController {
	@Autowired
	CompanyRestApiService comrestservice;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/company_token")
	public Map<String, Object> getCompanyByToken(
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
				List<Company> company = comrestservice.getCompanyByToken(token);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("date", company);
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

	@GetMapping(value = "/company")
	public List<Company> getCompany() {
		return comrestservice.getCompany();
	}

	@PostMapping(value = "/company/esave")
	public Company create(@RequestBody Company com) {
		return comrestservice.saveCompany(com);
	}

//delete company	  
	@DeleteMapping("/company/{id}")
	public void deleteCompany(@PathVariable Integer id) {
		comrestservice.removeCompany(id);
	}

//get company by id
	@GetMapping("/company/{id}")
	public Company getCompanyById(@PathVariable Integer id) {
		return comrestservice.getCompanyById(id);
	}

	@PutMapping("/company/{id}")
	public void updateCompany(@PathVariable Integer id, @RequestBody Company company) {
		Company com = comrestservice.getCompanyById(id);
		com.setCom_name(company.getCom_name());
		comrestservice.updateCompany(com);
	}
}
