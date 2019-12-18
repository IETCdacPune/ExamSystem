package com.ietpune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.SubjectDAO;
import com.ietpune.model.Course;
import com.ietpune.model.Subject;

@Service
public class SubjectService {
	@Autowired
	SubjectDAO subjectDAO;

	public Subject addSubject(Subject s) {
		return subjectDAO.save(s);
	}

	public List<Subject> getAllSubject() {
		return subjectDAO.findAll();
	}

	public Subject getSubjectById(int id) {
		Optional<Subject> optSubject = subjectDAO.findById(id);
		if (optSubject.isPresent())
			return optSubject.get();
		return null;
	}

	public Optional<Subject> getSubjectByNameAndCourse(String name, Course course) {
		return subjectDAO.findByNameAndCourse(name,course);
	}

	public List<Subject> getAllSubjectByCourse(Course course) {
		
		return subjectDAO.findByCourse(course);
	}
	public List<Subject> getAllSubjectByCourse(int courseId) {
		// TODO Auto-generated method stub
		System.out.println("courseid............."+courseId);
		return subjectDAO.findByCourse(courseId);
	}


}
