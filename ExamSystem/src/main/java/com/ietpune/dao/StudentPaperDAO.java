package com.ietpune.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Paper;
import com.ietpune.model.Student;
import com.ietpune.model.StudentPaper;
@Repository
public interface StudentPaperDAO extends JpaRepository<StudentPaper, Integer>  {
	boolean existsByPaperAndStudent(Paper paper,Student student);

	List<StudentPaper> findByStudent(Student student);

	List<StudentPaper> findTop5ByPaperAndResultOrderByMarksDesc(Paper paper,String result);

	List<StudentPaper> findAllByPaper(Paper p);

	int countByPaperAndResult(Paper p, String string);


}


	
