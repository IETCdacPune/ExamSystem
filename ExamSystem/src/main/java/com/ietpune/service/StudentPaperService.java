package com.ietpune.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.StudentPaperDAO;
import com.ietpune.model.StudentPaper;

@Service
public class StudentPaperService {
	@Autowired StudentPaperDAO studentPaperDAO;
	public StudentPaper addStudentPaper(StudentPaper studentPaper) {
		return studentPaperDAO.save(studentPaper);
	}
}
