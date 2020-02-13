package com.ietpune.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ietpune.dao.RoleDAO;
import com.ietpune.dao.StudentDAO;
import com.ietpune.model.Course;
import com.ietpune.model.Paper;
import com.ietpune.model.Role;
import com.ietpune.model.RoleName;
import com.ietpune.model.Student;
import com.ietpune.model.StudentPaper;
@Service
public class StudentService{
@Autowired private StudentDAO studentDAO;
@Autowired private BCryptPasswordEncoder passwordEcoder;
@Autowired private RoleDAO roleDAO;
@Autowired private CourseService courseService;

	public Student save(@Valid Student student) {
		student.setPassword(passwordEcoder.encode(student.getPassword()));
		List<Role> roles= new LinkedList<>();
		Optional<Role> optRole=roleDAO.findByRoleName(RoleName.STUDENT);
		if(optRole.isPresent())
			roles.add(optRole.get());
		student.setRoles(roles);
		student=studentDAO.save(student);
		return student;
	}

	public boolean findPrn(String prn) {
		Optional<Student> opt = studentDAO.findByPrn(prn);
		return opt.isPresent();
	}
	public Optional<Student> getByPrn(String prn) {
		 return studentDAO.findByPrn(prn);
	}
	public List<Paper> getAllNewPaperList(String prn) {
		return studentDAO.findPaperByPrn(prn);
	}


	public List<Student> getAllStudentList() {
		Optional<Course> c=courseService.findByName("DAC");
		List<Student> slist=new ArrayList<>();
		if(c.isPresent())
		{
			
			 slist=studentDAO.findByCourse(c.get());
			
		}
		return slist;
	}


	public Optional<Student> getStudentByPrn(String prn) {
		return studentDAO.findByPrn(prn);
	}

	public Student update(Student stud) {
		return studentDAO.save(stud);
	}

	public boolean findEmail(String emailId) {
		Optional<Student> opt = studentDAO.findByEmailId(emailId);
		return opt.isPresent();
	}

	
	
	  public List<Student> getAllStudentCourseWise(Course course) { 
	  return studentDAO.findAllByCourse(course); }

	public List<Student> getAllStudentCourseWise(Course course, List<String> studentPrnList) {
		// TODO Auto-generated method stub
		return studentDAO.findByCourseAndPrnNotIn(course,studentPrnList);
	}

	public List<Student> getAllPredacStudentList() {
		Optional<Course> c=courseService.findByName("PREDAC");
		List<Student> slist=new ArrayList<>();
		if(c.isPresent())
		{
			
			 slist=studentDAO.findByCourse(c.get());
			
		}
		return slist;
	}
	 

	
	
	







}
