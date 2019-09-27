package com.ietpune.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.User;
@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	Optional<User> findByPrn(String username);

}
