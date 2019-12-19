package com.ietpune.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Course;
import com.ietpune.model.Subject;
@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {
	List<Subject> findByCourse(Course course);
	Optional<Subject> findByNameAndCourse(String name, Course course);
	
}
