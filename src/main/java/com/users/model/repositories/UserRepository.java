package com.users.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.entities.User;

/**
 * JPA repository for CRUD operations.
 * 
 * @author LENOVO
 *
 */
@Repository
public interface UserRepository	extends JpaRepository<User, Long>{

	/**
	 * 
	 * To check user by mobile is already exists, to avoid two user with same number.
	 * 
	 * @param mobileNumber
	 * @return
	 */
	boolean existsByMobileNumber(String mobileNumber);
}
