package com.ietpune.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ietpune.model.Student;

@Service
public interface StudentService {



	Student save(@Valid Student student);



}
