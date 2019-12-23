package com.java.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.java.model.Company;
import com.java.model.Employee;
import com.java.service.CompanyService;
import com.java.service.EmployeeService;
import com.java.util.PDFGenerator;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	CompanyService comservice;
	@Autowired
	EmployeeService empservice;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView showRegistration(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login = (String) session.getAttribute("login_as");
		// System.out.println(login);
		if ("employee".equals(login) && (email != null)) {
			return new ModelAndView("redirect:/company/emp_introduction");

		} else if ("company".equals(login) && (email != null)) {
			return new ModelAndView("redirect:/company/introduction");

		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("registration");
			return page;
		}
	}

//use to save the company from registration form
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam("com_name") String com_name, @RequestParam("com_email") String com_email,
			@RequestParam("com_pass") String com_pass, @RequestParam("image") MultipartFile file) throws IOException {

		String hashedPassword = passwordEncoder.encode(com_pass);

		byte[] bFile = new byte[(int) file.getSize()];
		bFile = file.getBytes();
		Company com = new Company();
		com.setCom_email(com_email);
		com.setCom_name(com_name);
		com.setCom_pass(hashedPassword);
		com.setImage(bFile);
		comservice.saveCompany(com);

		ModelAndView page = new ModelAndView();
		page.setViewName("successRegister");

		return page;
	}

	@RequestMapping(value = "/company_info")
	@ResponseBody
	public Map<String, String> getCompanyInfo(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		int sid = (Integer) session.getAttribute("id");
		String email = (String) session.getAttribute("email");
		Company comp = comservice.getCompanyById(sid);
		// List<Company> com = comservice.getCompanyByEmail(email);
		int com_id = comp.getId();
		String com_id1 = Integer.toString(com_id);
		String name = comp.getCom_name();
		String com_email = comp.getCom_email();
		String random_key = comp.getRandom_key();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", com_email);
		map.put("com_id", com_id1);
		map.put("random_key", random_key);
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

	// company introduction profile
	@RequestMapping(value = "/introduction", method = RequestMethod.GET)
	public ModelAndView introduction(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");

		if ("company".equals(login_as) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");
			Company com = comservice.getCompanyById(sid);
			ModelAndView page = new ModelAndView();
			page.setViewName("company_intro");
			return page;

		} else if ("employee".equals(login_as) && (email != null)) {
			return new ModelAndView("redirect:/company/emp_introduction");
		}

		else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");
			return page;
		}

	}

	// viewing employee associated with the company
	@RequestMapping(value = "/employee_view", method = RequestMethod.GET)
	public ModelAndView getEmployee(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");
		if ("employee".equals(login_as) && (email != null)) {
			return new ModelAndView("redirect:/company/emp_introduction");
		}

		else if ("company".equals(login_as) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");

			Company com = comservice.getCompanyById(sid);
			String name = com.getCom_name();
			ModelAndView page = new ModelAndView();
			page.setViewName("employee_view");
			if (com.getImage() != null) {
				byte[] encoded = Base64.encodeBase64(com.getImage());
				String encodedString = new String(encoded);
				page.getModelMap().put("image", encodedString);
			} else {
				String image = com.getPicture_url();
				page.addObject("image1", image);
			}
			page.addObject("id", sid);
			page.addObject("name", name);
			return page;

		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");
			return page;
		}

	}

//for ajax call in employee_view
	@RequestMapping(value = "/employeePagination/{pageNo}/{propertyPerPage}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getEmployeePage(HttpServletRequest request, @PathVariable Integer pageNo,
			@PathVariable Integer propertyPerPage) throws ParseException {
		System.out.println(pageNo);
		HttpSession session = request.getSession();
		int sid = (Integer) session.getAttribute("id");
		String search = request.getParameter("search[value]");
		System.out.println("search value is:" + search);
		int page_id = pageNo;
		int total = propertyPerPage;
		if (page_id == 1) { // do nothing!

		} else {
			page_id = (page_id - 1) * total + 1;
		}
		List<Employee> emp = empservice.getEmployeesByPage(sid, page_id, total, search);
		Long search_size = empservice.countEmployeesBySearch(sid, search);
		System.out.println(emp);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("data", emp);
		map.put("recordsTotal", search_size);
		map.put("recordsFiltered", search_size);
		return map;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUserPage(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");

		if ("company".equals(login_as) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");
			List<Employee> us = empservice.getEmployeeByIdandCom(id, sid);

			int com_id = 0;
			for (Employee emp : us) {
				com_id = emp.getCom_id();
			}
			if (com_id == sid) {

				ModelAndView page = new ModelAndView();
				page.setViewName("edit_employeeinfo");
				return page;

			} else {
				return new ModelAndView("redirect:/company/employee_view");
			}

		} else if ("employee".equals(login_as) && (email != null)) {

			return new ModelAndView("redirect:/company/emp_introduction");
		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");

			return page;
		}

	}

	@RequestMapping(value = "/editEmployee_info")
	@ResponseBody
	public Map<String, String> getEmployeeInfoForEdit(HttpServletRequest request) throws ParseException {
		int id = Integer.parseInt(request.getParameter("data1"));
		System.out.println(id);
		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");
		if ("company".equals(login_as) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");
			Company com = comservice.getCompanyById(sid);
			List<Employee> us = empservice.getEmployeeByIdandCom(id, sid);

			String com_name = com.getCom_name();
			int emp_id = 0;
			String emp_name = null;
			String dob = null;
			String gender = null;
			String contact = null;
			String emp_email = null;
			String address = null;
			String city = null;
			String state = null;
			String pincode = null;
			String doj = null;
			String password = null;

			for (Employee emp : us) {
				emp_id = emp.getId();
				emp_name = emp.getName();
				dob = emp.getDob();
				gender = emp.getGender();
				contact = emp.getContact_no();
				emp_email = emp.getEmail();
				address = emp.getAddress();
				city = emp.getCity();
				pincode = emp.getPincode();
				doj = emp.getDoj();
				password = emp.getPassword();
				state = emp.getState();
			}

			Map<String, String> map = new HashMap<String, String>();
			map.put("com_id", Integer.toString(sid));
			map.put("emp_id", Integer.toString(emp_id));
			map.put("emp_name", emp_name);
			map.put("dob", dob);
			map.put("gender", gender);
			map.put("contact", contact);
			map.put("emp_email", emp_email);
			map.put("address", address);
			map.put("city", city);
			map.put("state", state);
			map.put("pincode", pincode);
			map.put("doj", doj);
			map.put("password", password);
			map.put("com_name", com_name);

			// String encodedString=null;
			if (com.getImage() != null) {
				byte[] encoded = Base64.encodeBase64(com.getImage());
				String encodedString = new String(encoded);
				map.put("image", encodedString);
			}

			else {
				String picture = com.getPicture_url();
				map.put("imageurl", picture);
			}

			System.out.print(map);
			return map;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView edditingUser(@ModelAttribute("user") Employee emp, HttpServletRequest request) {
		HttpSession session = request.getSession();
		empservice.updateEmployee(emp);
		return new ModelAndView("redirect:/company/employee_view");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");
		if ("employee".equals(login_as) && (email != null)) {

			return new ModelAndView("redirect:/company/emp_introduction");
		}

		else if ("company".equals(login_as) && (email != null)) {
			// int sid = (Integer) session.getAttribute("id");
			// User user = service.getUserById(id);
			empservice.removeEmployee(id);
			return new ModelAndView("redirect:/company/employee_view");

		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");
			return page;
		}
	}

	// download the employee list in pdf
	@GetMapping(value = "/empdownload", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customerReport(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");
		if ("company".equals(login_as) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");
			List<Employee> employee = empservice.employeeByComId(sid);
			Company company = comservice.getCompanyById(sid);

			ByteArrayInputStream bis = PDFGenerator.customerPDFReport(employee, company);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=employee_list.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
		}

		else if ("employee".equals(login_as) && (email != null)) {

			return null;
		} else {
			return null;
		}
	}

	@RequestMapping("/add_employee")
	public ModelAndView showform(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");
		if ("employee".equals(login_as) && (email != null)) {
			return new ModelAndView("redirect:/company/emp_introduction");
		}

		else if ("company".equals(login_as) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");
			Company com = comservice.getCompanyById(sid);
			ModelAndView page = new ModelAndView();
			page.setViewName("add_employee");
			m.addAttribute("command", new Employee());
			return page;
		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");
			return page;
		}
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(HttpServletRequest request, @ModelAttribute("user") Employee user) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login_as = (String) session.getAttribute("login_as");

		if ("employee".equals(login_as) && (email != null)) {
			return new ModelAndView("redirect:/company/emp_introduction");
		} else if ("company".equals(login_as) && (email != null)) {
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			empservice.saveEmployee(user);
			// int com_id = user.getCom_id();
			// m.addAttribute("com_id", com_id);
			return new ModelAndView("redirect:/company/employee_view");

		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");
			return page;
		}
	}

	// representing the line chart doj vs no. of employee
	@RequestMapping("/line")
	public ModelAndView showHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login = (String) session.getAttribute("login_as");
		System.out.println(login);
		if ("employee".equals(login) && (email != null)) {
			return new ModelAndView("redirect:/company/emp_introduction");

		} else if ("company".equals(login) && (email != null)) {
			int sid = (Integer) session.getAttribute("id");
			Company com = comservice.getCompanyById(sid);

			ModelAndView page = new ModelAndView();
			page.setViewName("LineChart");
			return page;

		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");

			return page;
		}
	}

	@RequestMapping(value = "/linechartdata")
	@ResponseBody
	public String getDataFromDisplay1(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		int sid = (Integer) session.getAttribute("id");
		List<Employee> employee = empservice.employeeByComId(sid);

		Map<String, Integer> map = new HashMap<String, Integer>();

		for (Employee us : employee) {
			String date = us.getDoj();
			DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd"); // converting string to date
			Date da = (Date) inputFormatter.parse(date);

			DateFormat outputFormatter = new SimpleDateFormat("dd-MM-yyyy");// converting date to string again in
																			// dd-mm-yyyy formate
			String strDateTime = outputFormatter.format(da);
			System.out.println("==String date is : " + strDateTime);

			if (map.containsKey(strDateTime)) {
				int count = map.get(strDateTime); // counting no of employee join in same date
				map.put(strDateTime, count + 1);
			} else {
				map.put(strDateTime, 1);

			}
		}
		JsonArray jsonArrayCategory = new JsonArray();
		JsonArray jsonArraySeries = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {

			jsonArrayCategory.add(entry.getKey());
			jsonArraySeries.add(entry.getValue());
		}
		jsonObject.add("categories", jsonArrayCategory);
		jsonObject.add("series", jsonArraySeries);
		return jsonObject.toString();
	}

	// reset password for company
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ModelAndView resetPassword(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login = (String) session.getAttribute("login_as");
		if ("company".equals(login) && email != null) {
			// List<Company> company = service.getCompanyByEmail(email);
			// int sid = (Integer) session.getAttribute("id");
			ModelAndView page = new ModelAndView();
			page.setViewName("newPassword");
			return page;

		}

		else if ("employee".equals(login) && (email != null)) {
			return new ModelAndView("redirect:/company/emp_introduction");
		}

		else {
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");

			return page;
		}
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ModelAndView updatePass(HttpServletRequest request, Model model,
			@RequestParam("com_pass_old") String password, @RequestParam("com_pass") String new_password) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login = (String) session.getAttribute("login_as");
		List<Company> comp = comservice.getCompanyByEmail(email);
		String com_passwordDB = null;

		for (Company company : comp) {
			com_passwordDB = company.getCom_pass();
		}
		boolean userFound;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // comparing entered password with stored
																		// password
		userFound = encoder.matches(password, com_passwordDB);
		System.out.println("old password:" + password);
		System.out.println("database password:" + com_passwordDB);
		System.out.println("in updatePassword checking userfound: " + userFound);
		if (userFound == true) {
			String hashedPassword = passwordEncoder.encode(new_password);
			comservice.updatePassword(email, hashedPassword);
			String msg = "Your Password is Successfully Reset";
			model.addAttribute("msg", msg);
			ModelAndView page = new ModelAndView();
			page.setViewName("logout");
			return page;
		} else {
			return new ModelAndView("redirect:/company/resetpassword");
		}

	}

	// random unique key generation
	@RequestMapping(value = "/comrandom_key", method = RequestMethod.POST)
	@ResponseBody
	public String randomKeyGenerator(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login = (String) session.getAttribute("login_as");

		if ("company".equals(login) && email != null) {

			String uniqueID = UUID.randomUUID().toString();
			comservice.updateRandomKey(email, uniqueID);
			System.out.print(uniqueID);

			return uniqueID;
		}

		else if ("employee".equals(login) && (email != null)) {
			return ("redirect:/company/emp_introduction");

		} else {
			return "/logout";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView openLogout(HttpSession session) {

		session.invalidate();
		// session = request.getSession(false);
		// System.out.println("Session : " + session);
		ModelAndView page = new ModelAndView();
		page.setViewName("logout");
		String msg = "You are Successfully Logout";
		page.addObject("msg", msg);
		return page;
	}
}
