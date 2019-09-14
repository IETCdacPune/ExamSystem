package com.ietpune.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Paper;
import com.ietpune.model.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{

	List<Question> findByPaper(Paper paper);

}
