package com.ietpune.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Student;
@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	Optional<Student> findByPrn(String prn);
}
