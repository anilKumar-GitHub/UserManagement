package com.users.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.users.models.entities.User;

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
	
	
	/**
	 * Get users by first name
	 * 
	 * @param firstName
	 * @return
	 */
	
	@Query("SELECT u FROM User u WHERE u.firstName LIKE :firstName")
	List<User> findByFirstNameLike(@Param("firstName") String firstName);

}
