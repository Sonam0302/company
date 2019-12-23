package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Company;
import com.java.model.Employee;
import com.java.service.CompanyService;
import com.java.service.EmployeeService;

@Controller
@RequestMapping(value = "/company")
public class LoginController {

	@Autowired
	CompanyService comservice;
	@Autowired
	EmployeeService empservice;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView openPage() {
		ModelAndView page = new ModelAndView();
		page.setViewName("login");
		return page;
	}

	@RequestMapping(value = "/alllogin", method = RequestMethod.POST)
	public ModelAndView loginR(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("login_as") String login_as, HttpServletRequest request) {
		// checking user belong to company or employee
		if ("employee".equals(login_as)) {
			List<Employee> emp = empservice.getEmployeeByEmail(email);
			String dbPass = null;
			int com_id = 0;
			int user_id = 0;
			String emprandom_key = null;
			for (Employee ver : emp) {
				dbPass = ver.getPassword();
				com_id = ver.getCom_id();
				user_id = ver.getId();
				emprandom_key = ver.getEmployeeRandom_key();
			}

			boolean userFound;
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // comparing entered password with stored
																			// password
			userFound = encoder.matches(password, dbPass);
			if (userFound == true) {
				Company co = comservice.getCompanyById(com_id); // for company logo finding company id the user belong
																// to
																// company
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("id", user_id);
				session.setAttribute("login_as", login_as);

				ModelAndView page = new ModelAndView();
				page.setViewName("employee");

				if (co.getImage() != null) {
					byte[] encoded = Base64.encodeBase64(co.getImage()); // for displaying image,encoding the image
					String encodedString = new String(encoded);
					page.getModelMap().put("image", encodedString);
				} else {
					String image = co.getPicture_url();
					page.addObject("image1", image);
				}
				page.addObject("random_key", emprandom_key);
				page.addObject("user", emp);
				return page;
			} else {
				ModelAndView page = new ModelAndView();
				page.setViewName("incorrect");
				return page;
			}

		}
		/* checking if company is logining or not */
		else if ("company".equals(login_as)) {
			List<Company> company = comservice.getCompanyByEmail(email);
			String comdbPass = null;
			int id = 0;
			byte[] image = null;
			String picture = null;
			String random_key = null;
			for (Company comP : company) {
				comdbPass = comP.getCom_pass();
				id = comP.getId();
				image = comP.getImage();
				picture = comP.getPicture_url();
				random_key = comP.getRandom_key();
			}
			boolean comFound;
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			comFound = encoder.matches(password, comdbPass);
			System.out.println("password enter by the user while login" + password);
			System.out.println("password from database" + comdbPass);
			if (comFound == true) {
				// Company co = service.getCompanyById(id);

				HttpSession session = request.getSession(); // creating session
				session.setAttribute("email", email); // setting of session variable
				session.setAttribute("id", id);
				session.setAttribute("login_as", login_as);

				ModelAndView page = new ModelAndView();
				page.setViewName("admin");

				if (image != null) {
					byte[] encoded = Base64.encodeBase64(image);
					String encodedString = new String(encoded);
					page.getModelMap().put("image", encodedString);
				} else {

					page.addObject("image1", picture);
				}
				// String random_key = co.getRandom_key();
				page.addObject("random_key", random_key);
				page.addObject("company", company);
				// page.addObject("login_as", login_as);
				return page;
			} else {
				ModelAndView page = new ModelAndView();
				page.setViewName("incorrect");
				return page;
			}
		} else {
			ModelAndView page = new ModelAndView();
			page.setViewName("incorrect");
			return page;
		}

	}

}
