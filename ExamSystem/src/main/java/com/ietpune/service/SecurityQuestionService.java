package com.ietpune.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.SecurityQuestionDAO;
import com.ietpune.model.SecurityQuestion;

@Service
public class SecurityQuestionService {
	@Autowired SecurityQuestionDAO securityQuestionDAO;

	public Optional<SecurityQuestion> getQuestionById(int sqId) {
		return securityQuestionDAO.findById(sqId);
	}
	
}
