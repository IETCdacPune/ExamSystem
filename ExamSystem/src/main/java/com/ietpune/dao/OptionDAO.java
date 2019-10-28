package com.ietpune.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Option;
import com.ietpune.model.Question;

@Repository
public interface OptionDAO extends JpaRepository<Option,Integer>{

	List<Option> findByQuestion(Question question);
	
}
