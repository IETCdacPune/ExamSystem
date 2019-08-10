package com.ietpune.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Paper;
@Repository
public interface PaperDAO extends JpaRepository<Paper, Integer> {

}
