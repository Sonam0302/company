package com.java.config;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableOAuth2Sso
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.antMatcher("/**").authorizeRequests()
				.antMatchers("/", "/callback", "/login**", "/webjars/**", "/error**", "/registration", "/company/",
						"/company/registration", "/company/save", "/company/alllogin", "/company/add_employee",
						"/company/saveEmployee", "/company/employeePagination/{pageNo}/{propertyPerPage}",
						"/company/edit", "/company/delete", "/company/introduction", "/company/empdownload",
						"/company/line", "/company/linechartdata", "/company/logout", "/company/employee_view",
						"/company/emp_introduction", "/company/myForm", "/company/resetpassword",
						"/company/updatePassword", "/company/random_key")
				.permitAll();
	}

}
