package com.ietpune;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import com.ietpune.dao.RoleDAO;
import com.ietpune.dao.SecurityQuestionDAO;
import com.ietpune.dao.UserDAO;
import com.ietpune.model.Role;
import com.ietpune.model.RoleName;
import com.ietpune.model.SecurityQuestion;
import com.ietpune.model.User;

@SpringBootApplication
public class ExamSystemApplication implements CommandLineRunner {
	@Autowired private RoleDAO roleDAO;
	@Autowired private UserDAO userDAO;
	@Autowired private BCryptPasswordEncoder passwordEcoder;
	@Autowired private SecurityQuestionDAO securityQuestionDAO;
	@Value("${admin.username}")
	private String adminName;
	@Value("${admin.password}")
	private String adminPass;

	public static void main(String[] args) {
		SpringApplication.run(ExamSystemApplication.class, args);
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
	public Map<String, Boolean> myMap() {
		final Map<String, Boolean> myMap = new HashMap<>();
		myMap.put("isRegistrationAvailable", true);
		myMap.put("isAddAvailable", false);
		return myMap;
	}
	@Bean(name = "demo")
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
	public String demo() {
		return "kahipan";
	}
	@Override
	public void run(String... args) throws Exception {
		//System.out.println("abc@1234 :- "+passwordEcoder.encode("abc@1234"));
		if (!roleDAO.findByRoleName(RoleName.ADMIN).isPresent()) {
			roleDAO.save(new Role(RoleName.ADMIN));
		}
		if (!roleDAO.findByRoleName(RoleName.STUDENT).isPresent()) {
			roleDAO.save(new Role(RoleName.STUDENT));
		}
		if (!userDAO.findByPrn(adminName).isPresent()) {
			List<Role> roles = new LinkedList<>();
			Optional<Role> optRole = roleDAO.findByRoleName(RoleName.ADMIN);
			if (optRole.isPresent()) {
				roles.add(optRole.get());
			}
			userDAO.save(new User(adminName, passwordEcoder.encode(adminPass),true, roles));
		}
		if (securityQuestionDAO.findAll().isEmpty()) {
			securityQuestionDAO.save(new SecurityQuestion("What is your nick name?"));
			securityQuestionDAO.save(new SecurityQuestion("Where you mate your Lover?")); 
			securityQuestionDAO.save(new SecurityQuestion("What is your childhood favorite movie?")); 
			securityQuestionDAO.save(new SecurityQuestion("What was your favorite sport in high school?"));
		}
	}

}
