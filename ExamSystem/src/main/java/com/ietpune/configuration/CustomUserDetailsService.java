package com.ietpune.configuration;

import java.util.Optional;

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
	public UserDetails loadUserByUsername(String username) {
		Optional<User> optUser = userDAO.findByPrn(username);
		if (!optUser.isPresent())
			throw new UsernameNotFoundException(username + " Not Found");
		return optUser.map(CustomUserDetails::new).orElse(null);
	}

}
