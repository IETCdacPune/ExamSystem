package com.ietpune.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.QuestionDAO;
import com.ietpune.model.Paper;
import com.ietpune.model.Question;
@Service
public class QuestionService {
	@Autowired QuestionDAO questionDAO;
	public List<Question> getAllQuestionOfPaper(Paper paper) {
		return questionDAO.findByPaper(paper);
	}

	

}
