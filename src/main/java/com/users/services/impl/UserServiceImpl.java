package com.users.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.exceptions.UserAlreadExistsException;
import com.users.exceptions.UserNotFoundException;
import com.users.model.dto.UserDTO;
import com.users.model.entity.User;
import com.users.model.repositories.UserRepository;
import com.users.services.UserService;
import com.users.utils.ModelMapperUtil;

/**
 * 
 * User service implementation to implement all CRUD operations
 * 
 * @author anilKumar
 *
 */
@Service
public class UserServiceImpl implements UserService {


	// JPA repository
	@Autowired
	private UserRepository userRepository;


	/**
	 * Get all users list.
	 * 
	 * @return
	 */
	@Override
	public List<User> getAllUsers() {

		return this.userRepository.findAll();
	}

	/**
	 * Get user by user-id
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public User getUserById(Long id) {

		Optional<User> userOptional = this.userRepository.findById(id);
		
		if(userOptional.isEmpty())	{
			throw new UserNotFoundException(id);
		}
		
		return userOptional.get();
	}

	/**
	 * Adding new user to list.
	 * 
	 * @param userData
	 * @return
	 */
	@Override
	public User addNewUserEntry(UserDTO userData) {

		if(this.userRepository.existsByMobileNumber(userData.getMobileNumber()))	{
			throw new UserAlreadExistsException(userData.getMobileNumber());
		}

		return this.userRepository.saveAndFlush((User) ModelMapperUtil.map(userData, User.class));
	}


	/**
	 * Updating existing user by user id
	 * 
	 * @param id
	 * @param userData
	 * @return
	 */
	@Override
	public User updateExistingUser(Long id, UserDTO userData) {

		if(!this.userRepository.existsById(id))	{
			throw new UserNotFoundException(id);
		}
		
		User user = this.userRepository.findById(id).get();
		user.setName(userData.getName());
		user.setDateOfBirth(userData.getDateOfBirth());
		user.setCity(userData.getCity());
		user.setMobileNumber(userData.getMobileNumber());
		
		return this.userRepository.saveAndFlush(user);
	}

	

	/**
	 * Delete user by user id and returns deleted user.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public User deleteUser(final Long id) {

		Optional<User> user = this.userRepository.findById(id);
		
		if(user.isPresent())	{
			this.userRepository.deleteById(id);
		} else {
			throw new UserNotFoundException(id);
		}
		
		return user.orElseGet(() -> null);
	}

	/**
	 * Delete all user, restricted operation.
	 * 
	 * @return
	 */
	@Override
	public long deleteAllUser() {

		long count = this.userRepository.count();
		
		this.userRepository.deleteAll();
		
		return count;
	}
}
