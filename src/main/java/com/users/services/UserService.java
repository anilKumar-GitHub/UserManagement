package com.users.services;

import java.util.List;

import com.users.models.dtos.UserDTO;

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
	List<UserDTO> getAllUsers();

	/**
	 * Get user by user-id
	 * 
	 * @param id
	 * @return
	 */
	UserDTO getUserById(final Long id);

	/**
	 * Get user by user-id
	 * 
	 * @param id
	 * @return
	 */
	List<UserDTO> getUserByNameLike(String firstName);

	/**
	 * Adding new user to list.
	 * 
	 * @param userData
	 * @return
	 */
	UserDTO addNewUserEntry(UserDTO userData);

	/**
	 * Updating existing user by user id
	 * 
	 * @param id
	 * @param userData
	 * @return
	 */
	UserDTO updateExistingUser(final Long id, UserDTO userData);

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
	void deleteUser(final Long id);
}
