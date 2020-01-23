package com.ietpune.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.StudentPaper;
@Repository
public interface StudentPaperDAO extends JpaRepository<StudentPaper, Integer>  {

}
