package com.ietpune.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.CourseDAO;
import com.ietpune.dao.SubjectDAO;
import com.ietpune.model.Course;

@Service
public class CourseService {
	@Autowired CourseDAO courseDAO;
	@Autowired SubjectDAO subjectDAO;
	private Random rand = new Random();
	public Course addCourse(Course c) throws SQLException{
		if(!courseDAO.findByName(c.getName()).isPresent())
			return courseDAO.save(c);
		else
			throw new SQLException("Course already exist...");
	}

	public String getCourseCode() {
		while(true) {
			String code=genrateRandomeCode();
			if(!courseDAO.findByCourseCode(code).isPresent())
				return code;
		}
	}
	private String genrateRandomeCode() {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		int n=6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			sb.append(alphaNumericString.charAt(rand.nextInt(alphaNumericString.length())));
		}
		return sb.toString();
	}

	public List<Course> getAllCourses() {
		List<Course> list =courseDAO.findAll();
		
		return list;
	}

	public List<Course> getAllCoursesWithSubject() {
		List<Course> cList=courseDAO.findAll();
		cList.forEach(c->c.setSubjectList(subjectDAO.findByCourse(c)));
		return cList;
	}

	public Optional<Course> getCourseById(int id) {
		return courseDAO.findById(id);
	}

	public Optional<Course> getCourseByCode(String courseCode) {
		return courseDAO.findByCourseCode(courseCode);
	}

	public List<Course> getAllCoursesWithEagerLoad() {
		List<Course> list=getAllCourses();
		list.forEach(
				
				(course)->{
					
					course.setSubjectList(subjectDAO.findByCourse(course));
				});
		return list;
	}
}
