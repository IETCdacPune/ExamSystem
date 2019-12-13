package com.ietpune.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.OptionDAO;
import com.ietpune.dao.QuestionDAO;
import com.ietpune.dao.SecurityQuestionDAO;
import com.ietpune.model.Options;
import com.ietpune.model.Paper;
import com.ietpune.model.Question;
import com.ietpune.model.SecurityQuestion;

@Service
public class QuestionService {
	@Autowired QuestionDAO questionDAO;
	@Autowired OptionDAO optionDAO;
	@Autowired SecurityQuestionDAO securityQuestionDAO;
	public List<Question> getAllQuestionOfPaper(Paper paper) {
		// questionDAO.ge
		return questionDAO.findByPaper(paper);
	}

	public Question getQuestionById(int id) {
		Optional<Question> optQuestion = questionDAO.findById(id);
		if (optQuestion.isPresent())
			return optQuestion.get();
		return null;
	}

	public @Valid Question editQuestion(@Valid Question question) {
		return questionDAO.save(question);
	}

	public List<Options> getAllOptions(Question question) {
		return optionDAO.findByQuestion(question);
	}
	public List<SecurityQuestion> getAllSecurityQuestion(){
		return securityQuestionDAO.findAll();
	}
}
