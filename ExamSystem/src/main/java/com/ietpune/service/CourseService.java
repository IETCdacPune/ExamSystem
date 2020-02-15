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
	@Autowired FileService fileService;
	public Course addCourse(Course c) throws SQLException{
		if(!courseDAO.findByName(c.getName()).isPresent())
			return courseDAO.save(c);
		else
			throw new SQLException("Course already exist...");
	}

	public String getCourseCode() {
		while(true) {
			String code=fileService.genrateRandomeCode(6);
			if(!courseDAO.findByCourseCode(code).isPresent())
				return code;
		}
	}
	

	public List<Course> getAllCourses() {
		return courseDAO.findAll();
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
		list.forEach(course->course.setSubjectList(subjectDAO.findByCourse(course)));
		return list;
	}

	public Optional<Course> findByName(String name) {
		// TODO Auto-generated method stub
		return courseDAO.findByName(name);
	}
}
