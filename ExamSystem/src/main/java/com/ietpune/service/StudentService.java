package com.ietpune.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ietpune.dao.RoleDAO;
import com.ietpune.dao.StudentDAO;
import com.ietpune.model.Paper;
import com.ietpune.model.Role;
import com.ietpune.model.RoleName;
import com.ietpune.model.Student;
@Service
public class StudentService{
@Autowired private StudentDAO studentDAO;
@Autowired private BCryptPasswordEncoder passwordEcoder;
@Autowired private RoleDAO roleDAO;

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
		return studentDAO.findAll();
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

	







}
