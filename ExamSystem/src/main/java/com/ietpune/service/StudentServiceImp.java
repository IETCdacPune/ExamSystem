package com.ietpune.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.StudentDAO;
import com.ietpune.model.Student;
@Service
public class StudentServiceImp implements StudentService {
@Autowired
StudentDAO studentDAO;


	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public Student save(@Valid Student student) {
		// TODO Auto-generated method stub
		student=studentDAO.save(student);
		if(student==null)
		{
			
			
			return null;
		}
		
		return student;
	}

}
