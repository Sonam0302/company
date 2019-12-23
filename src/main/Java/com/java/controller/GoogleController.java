package com.java.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Company;
import com.java.service.CompanyService;
import com.java.service.EmployeeService;

@Controller
public class GoogleController {

	@Autowired
	CompanyService comservice;
	@Autowired
	EmployeeService empservice;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender javaMailSender;

	@RequestMapping(value = "/callback")
	public String signupPage(WebRequest request, Model model, HttpServletRequest req, HttpSession session)
			throws MalformedURLException, IOException, ParseException {
		session.invalidate();
		String code = request.getParameter("code");
		System.out.println(code);
		String tokeUrl = "https://www.googleapis.com/oauth2/v4/token";
		HttpsURLConnection httpClient = (HttpsURLConnection) new URL(tokeUrl).openConnection();
		String client_id = "931062670962-h40kejm06seg3vuvtvicoe1vm302mm0t.apps.googleusercontent.com";
		String redirect_uri = "http://localhost:8080/callback";
		String c_s = "Xu5QF9SmRYBTHOSuCkn-o1m1";
		String scope = "https://www.googleapis.com/auth/userinfo.email";
		// add request header
		httpClient.setConnectTimeout(5000);
		httpClient.setRequestMethod("POST");
		httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "code=" + code + "&redirect_uri=" + redirect_uri + "&client_id=" + client_id
				+ "&client_secret=" + c_s + "&scope=" + scope + "&grant_type=authorization_code";
		httpClient.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
			wr.writeBytes(urlParameters);
			wr.flush();
		}

		int responseCode = httpClient.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + tokeUrl);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		String res = null;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

			String line;
			StringBuilder response = new StringBuilder();

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			// print result
			System.out.println(response.toString());
			res = response.toString();
		} finally {
			httpClient.disconnect();
		}
		System.out.println("check " + res);
		JSONParser parser = new JSONParser();
		JSONObject newJObject = (JSONObject) parser.parse(res);
		String accesstoken = (String) newJObject.get("access_token");
		System.out.println(accesstoken);

		// http get request

		String proUrl = "https://www.googleapis.com/userinfo/v2/me";
		HttpsURLConnection httpClient1 = (HttpsURLConnection) new URL(proUrl).openConnection();
		httpClient1.setRequestMethod("GET");
		httpClient1.setRequestProperty("Authorization", "Bearer " + accesstoken + "");
		// String urlParameter = "Bearer=" + accesstoken + "";
		int responseCode1 = httpClient1.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + proUrl);
		System.out.println("Response Code : " + responseCode1);
		String res_profile = null;

		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient1.getInputStream()))) {

			String line1;
			StringBuilder response = new StringBuilder();

			while ((line1 = in.readLine()) != null) {
				response.append(line1);
			}

			// print result
			System.out.println(response.toString());
			res_profile = response.toString();
		} finally {
			httpClient1.disconnect();
		}
		JSONParser parser1 = new JSONParser();
		JSONObject newJObject1 = (JSONObject) parser1.parse(res_profile);
		String google_id = (String) newJObject1.get("id");
		String name = (String) newJObject1.get("name");
		String picture = (String) newJObject1.get("picture");
		String email = (String) newJObject1.get("email");
		System.out.println(email);
		System.out.println(name);

		boolean userFound;
		userFound = comservice.findCompanyBygoogle(google_id, email);

		if (userFound == true) {

			List<Company> co = comservice.getCompanyByEmail(email);
			String company_email = null;
			int company_id = 0;

			for (Company comp : co) {
				company_email = comp.getCom_email();
				company_id = comp.getId();
			}
			// creating session
			session = req.getSession();
			session.setAttribute("email", company_email); // setting of session variable
			session.setAttribute("id", company_id);
			session.setAttribute("login_as", "company");
			// redirectAttributes.addFlashAttribute("company", co);
			return ("redirect:/company/introduction");
		}

		else {
			// session.invalidate();
			// generating random password
			List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 2),
					new CharacterRule(EnglishCharacterData.LowerCase, 2),
					new CharacterRule(EnglishCharacterData.Digit, 2),
					new CharacterRule(EnglishCharacterData.Special, 2));

			PasswordGenerator generator = new PasswordGenerator();
			String password = generator.generatePassword(10, rules);

			System.out.println("the random generated password :" + password);
			model.addAttribute("google_id", google_id);
			model.addAttribute("com_name", name);
			model.addAttribute("image", picture);
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			ModelAndView page = new ModelAndView();
			page.setViewName("signup");
			return "/signup";
		}

	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView showRegistration(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String login = (String) session.getAttribute("login_as");
		// System.out.println(login);
		ModelAndView page = new ModelAndView();
		page.setViewName("signup");
		return page;
	}

	// saving user through goolge sign in
	@RequestMapping(value = "/company/myForm", method = RequestMethod.POST)
	public ModelAndView saveCompany(@RequestParam("google_id") String google_id,
			@RequestParam("com_name") String com_name, @RequestParam("com_email") String com_email,
			@RequestParam("com_pass") String com_pass, @RequestParam("picture_url") String pic) throws IOException {

		String hashedPassword = passwordEncoder.encode(com_pass);

		Company com = new Company();
		com.setGoogle_id(google_id);
		com.setCom_email(com_email);
		com.setCom_name(com_name);
		com.setCom_pass(hashedPassword);
		com.setPicture_url(pic);

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(com_email);
		msg.setSubject("Confirmation Mail");
		msg.setText("Dear " + com_name + " you are successfully registered your \n your passowrd=" + com_pass
				+ " \n please change your password after first login");

		javaMailSender.send(msg);
		comservice.saveCompany(com);
		ModelAndView page = new ModelAndView();
		page.setViewName("successRegister");
		return page;
	}
}
