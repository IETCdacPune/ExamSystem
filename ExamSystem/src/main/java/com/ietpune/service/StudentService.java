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
		roles.add(roleDAO.findByRole(RoleName.STUDENT).get());
		student.setRoles(roles);
		student=studentDAO.save(student);
		if(student==null)
			return null;
		return student;
	}

	public boolean findPrn(String prn) {
		Optional<Student> opt = studentDAO.findByPrn(prn);
		if(opt.isPresent())
			return true;
		return false;
	}

}
