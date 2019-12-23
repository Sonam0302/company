package com.java.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Company;
import com.java.model.Employee;
import com.java.service.CompanyService;
import com.java.service.EmployeeService;

@Controller
@RequestMapping(value = "/company")
public class EmployeeController {

	@Autowired
	CompanyService comservice;
	@Autowired
	EmployeeService empservice;

	@RequestMapping(value = "/emp_introduction", method = RequestMethod.GET)
	public ModelAndView empIntroduction(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");

		if ("employee".equals(login_as) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");
			List<Employee> user = empservice.getEmployeeByEmail(email);
			Employee us = empservice.getEmployeeById(sid);
			int com_id = us.getCom_id();
			Company com = comservice.getCompanyById(com_id);
			ModelAndView page = new ModelAndView();
			page.setViewName("employee_intro");

			if (com.getImage() != null) {
				byte[] encoded = Base64.encodeBase64(com.getImage());
				String encodedString = new String(encoded);
				page.getModelMap().put("image", encodedString);
			} else {
				String image = com.getPicture_url();
				page.addObject("image1", image);
			}

			page.addObject("id", sid);
			page.addObject("user", user);
			return page;

		} else if ("company".equals(login_as) && (email != null)) {
			return new ModelAndView("redirect:/company/introduction");

		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");

			return page;
		}

	}

	@RequestMapping(value = "/emp_info")
	@ResponseBody
	public Map<String, String> getCompanyInfo(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		int sid = (Integer) session.getAttribute("id");
		String email = (String) session.getAttribute("email");
		List<Employee> employee = empservice.getEmployeeByEmail(email);
		int com_id = 0;
		String emp_name = null;
		String dob = null;
		String gender = null;
		String contact = null;
		String address = null;
		String city = null;
		String state = null;
		String pincode = null;
		String doj = null;
		String empRandom_key = null;
		for (Employee emp : employee) {
			emp_name = emp.getName();
			dob = emp.getDob();
			gender = emp.getGender();
			contact = emp.getContact_no();
			address = emp.getAddress();
			city = emp.getCity();
			state = emp.getState();
			pincode = emp.getPincode();
			doj = emp.getDoj();
			empRandom_key = emp.getEmployeeRandom_key();
			com_id = emp.getCom_id();

		}

		DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd"); // converting string to date
		Date da = (Date) inputFormatter.parse(dob);
		Date da1 = (Date) inputFormatter.parse(doj);

		DateFormat outputFormatter = new SimpleDateFormat("dd-MM-yyyy");// converting date to string again in
																		// dd-mm-yyyy formate
		String date_birth = outputFormatter.format(da);
		String date_joining = outputFormatter.format(da1);

		// DateFormat outputFormatter = new SimpleDateFormat("dd-MM-yyyy");// converting
		// date to string again in
//		String dateofb = outputFormatter.format(dob);
//		String dateofj = outputFormatter.format(doj);

		Company comp = comservice.getCompanyById(com_id);

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", emp_name);
		map.put("dob", date_birth);
		map.put("gender", gender);
		map.put("contact", contact);
		map.put("email", email);
		map.put("address", address);
		map.put("city", city);
		map.put("state", state);
		map.put("pincode", pincode);
		map.put("doj", date_joining);
		map.put("com_id", Integer.toString(com_id));
		map.put("emprandom_key", empRandom_key);
		if (comp.getImage() != null) {
			byte[] encoded = Base64.encodeBase64(comp.getImage());
			String encodedString = new String(encoded);
			map.put("image", encodedString);
			map.put("imageurl", null);
		}

		else {
			String picture = comp.getPicture_url();
			map.put("imageurl", picture);
			map.put("image", null);
		}

		System.out.print(map);
		return map;

	}

	// random unique key generation
	@RequestMapping(value = "/emprandom_key", method = RequestMethod.POST)
	@ResponseBody
	public String randomKeyGenerator(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login = (String) session.getAttribute("login_as");

		if ("company".equals(login) && email != null) {

			return ("redirect:/company/introduction");
		}

		else if ("employee".equals(login) && (email != null)) {
			String uniqueID = UUID.randomUUID().toString();
			empservice.updateEmployeeRandomKey(email, uniqueID);
			return uniqueID;
		} else {
			return "/logout";
		}
	}

}
