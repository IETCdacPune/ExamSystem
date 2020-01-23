package com.ietpune.dao;




import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Paper;
import com.ietpune.model.Student;
import com.ietpune.model.Subject;
@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	Optional<Student> findByPrn(String prn);
	
	
@Query("select sub.paperList from Student s inner join Subject sub on s.course.courseId=sub.course.courseId inner join Paper p on sub.id=p.subject.id where s.prn=:prn and p.newPaper=true")
	List<Paper> findSubjectByPrn(@Param("prn")String prn);
@Query("select sub.paperList from Student s inner join Subject sub on s.course.courseId=sub.course.courseId inner join Paper p on sub.id=p.subject.id where s.prn=:prn and p.newPaper=false")
List<Paper> findSubjectByPrns(@Param("prn")String prn);
	
	


}
