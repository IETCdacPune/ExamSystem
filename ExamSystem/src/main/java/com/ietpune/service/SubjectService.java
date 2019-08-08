package com.ietpune.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.SubjectDAO;
import com.ietpune.model.Subject;

@Service
public class SubjectService {
	@Autowired SubjectDAO subjectDAO;
	public Subject addSubject(Subject s) {
		return subjectDAO.save(s);
	}
	public List<Subject> getAllSubject(){
		return subjectDAO.findAll();
	}
	
}
