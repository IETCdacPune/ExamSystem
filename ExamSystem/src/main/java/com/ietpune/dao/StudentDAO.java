package com.ietpune.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Student;
import com.ietpune.model.Subject;
@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	Optional<Student> findByPrn(String prn);
@Query("select c.subjectList from Student s inner join s.course c where s.prn=:prn")
	List<Subject> findSubjectByPrn(@Param("prn")String prn);
	
	


}
