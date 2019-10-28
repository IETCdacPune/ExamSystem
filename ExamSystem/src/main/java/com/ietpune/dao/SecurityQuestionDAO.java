package com.ietpune.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ietpune.model.SecurityQuestion;

public interface SecurityQuestionDAO extends JpaRepository<SecurityQuestion, Integer> {

}
