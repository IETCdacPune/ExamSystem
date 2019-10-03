package com.ietpune;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ietpune.dao.RoleDAO;
import com.ietpune.dao.UserDAO;
import com.ietpune.model.Role;
import com.ietpune.model.RoleName;
import com.ietpune.model.User;

@SpringBootApplication
public class ExamSystemApplication implements CommandLineRunner{
	@Autowired RoleDAO roleDAO;
	@Autowired UserDAO userDAO;
	@Autowired BCryptPasswordEncoder passwordEcoder;
	@Value("${admin.username}")
    private String adminName;
	@Value("${admin.password}")
    private String adminPass;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamSystemApplication.class, args);
	}
	@Bean
	public Map<String, String> myMap() {
	    final Map<String, String> myMap = new HashMap<>();
	    myMap.put("isRegistrationAvailable", "false");
	    myMap.put("isAddAvailable", "false");	    
	    return myMap;
	}
	@Override
	public void run(String... args) throws Exception {
		Role role;
		try {
			role=roleDAO.findByRole(RoleName.ADMIN).get();
		}catch(Exception e) {
			role=new Role();
			role.setRole(RoleName.ADMIN);
			roleDAO.save(role);
		}
		try {
			role=roleDAO.findByRole(RoleName.STUDENT).get();
		}catch(Exception e) {
			role=new Role();
			role.setRole(RoleName.STUDENT);
			roleDAO.save(role);
		}
		
		User user;
		try {
			user=userDAO.findByPrn(adminName).get();
		}catch(Exception e) {
			Set<Role> roles= new HashSet<>();
			role=roleDAO.findByRole(RoleName.ADMIN).get();
			roles.add(role);
			role=roleDAO.findByRole(RoleName.STUDENT).get();
			roles.add(role);
			user=new User(adminName,passwordEcoder.encode(adminPass),roles);
			//user=new User(adminName,adminPass,roles);
			userDAO.save(user);
		}
	}
	
}
