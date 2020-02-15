package com.ietpune.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Course;
import com.ietpune.model.Paper;
import com.ietpune.model.Subject;
@Repository
public interface PaperDAO extends JpaRepository<Paper, Integer> {
	List<Paper> findBySubject(Subject sub);

	boolean existsBypaperCode(int paperCode);



	
}
