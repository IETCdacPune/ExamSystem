package com.ietpune.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Options;
import com.ietpune.model.Question;

@Repository
public interface OptionDAO extends JpaRepository<Options,Integer>{

	List<Options> findByQuestion(Question question);
	
}
