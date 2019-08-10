package com.ietpune.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Question;

@Repository
public interface QuestionPaperDAO extends JpaRepository<Question, Integer>{

}
