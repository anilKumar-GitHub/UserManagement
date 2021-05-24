package com.users.services;

import java.util.List;

import com.users.model.dto.UserDTO;
import com.users.model.entity.User;

/**
 * User service interface
 * 
 * @author anilKumar
 *
 */
public interface UserService {

	/**
	 * Get all users list.
	 * 
	 * @return
	 */
	List<User> getAllUsers();

	/**
	 * Get user by user-id
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(final Long id);

	/**
	 * Adding new user to list.
	 * 
	 * @param userData
	 * @return
	 */
	User addNewUserEntry(UserDTO userData);

	/**
	 * Updating existing user by user id
	 * 
	 * @param id
	 * @param userData
	 * @return
	 */
	User updateExistingUser(final Long id, UserDTO userData);

	/**
	 * Delete all user, restricted operation.
	 * 
	 * @return
	 */
	long deleteAllUser();
	
	/**
	 * Delete user by user id and returns deleted user.
	 * 
	 * @param id
	 * @return
	 */
	User deleteUser(final Long id);
}
