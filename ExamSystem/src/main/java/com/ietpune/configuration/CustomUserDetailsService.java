package com.ietpune.configuration;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername username="+username);
		Optional<User> opUser = userDAO.findByPrn(username);
		opUser.orElseThrow(() -> new UsernameNotFoundException(username+" Not Found"));
		return opUser.map(CustomUserDetails::new).get();
	}

}
