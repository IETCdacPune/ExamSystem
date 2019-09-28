package com.ietpune.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ietpune.dao.UserDAO;
import com.ietpune.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			System.out.println("Prn to find:" + username);
			User user = userDAO.findByPrn(username).get();
			System.out.println("user found:" + user);
			return new CustomUserDetails(user);
		} catch (Exception ex) {
			throw new UsernameNotFoundException("User PRN is not found");
		}
	}

}
