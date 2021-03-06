package com.ietpune.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Course;
import com.ietpune.model.Paper;
import com.ietpune.model.Student;
import com.ietpune.model.Subject;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	Optional<Student> findByPrn(String prn);

	@Query("select sub.paperList from Student s "
			+ "join Course cour "
			+ "join cour.subjectList sub "
			+ "join Paper p "
			+ "where s.prn=:prn and p.newPaper=true and p.enabled=true")
	List<Paper> findPaperByPrn(@Param("prn") String prn);


	List<Student> findAllByCourse(Course course);

	List<Student> findByCourseAndPrnNotIn(Course course, List<String> studentPrnList);



	List<Student> findByCourse(Course course);

	Optional<Student> findByEmailId(String emailId);




	

	int countByCourse(Course course);

}
