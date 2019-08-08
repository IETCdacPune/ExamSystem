package com.ietpune.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Subject;
@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {

}
