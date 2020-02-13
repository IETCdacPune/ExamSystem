package com.ietpune.dao;

import org.springframework.data.repository.CrudRepository;

import com.ietpune.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
