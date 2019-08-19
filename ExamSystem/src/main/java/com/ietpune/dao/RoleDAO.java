package com.ietpune.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ietpune.model.Role;
import com.ietpune.model.RoleName;
@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
	Optional<Role> findByRole(RoleName roleName);
}
