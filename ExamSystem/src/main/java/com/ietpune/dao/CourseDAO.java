package com.ietpune.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course,Integer> {

	Optional<Course> findByCourseCode(String code);

	Optional<Course> findByName(String name);

}
