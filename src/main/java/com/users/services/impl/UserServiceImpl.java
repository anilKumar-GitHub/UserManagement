package com.users.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.exceptions.UserAlreadExistsException;
import com.users.exceptions.UserNotFoundException;
import com.users.models.dtos.UserDTO;
import com.users.models.entities.User;
import com.users.models.repositories.UserRepository;
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
	public List<UserDTO> getAllUsers() {

		List<User> users = this.userRepository.findAll();
		
		return users.stream().map(UserDTO::mapToUserDTO).collect(Collectors.toList());
	}

	/**
	 * Get user by user-id
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public UserDTO getUserById(Long id) {

		Optional<User> userOptional = this.userRepository.findById(id);

		if(userOptional.isEmpty())	{
			throw new UserNotFoundException(id);
		}

		return UserDTO.mapToUserDTO(userOptional.get());
	}

	/**
	 * Get user by user-id
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<UserDTO> getUserByNameLike(String firstName) {

		return this.userRepository.findByFirstNameLike(firstName + "%")
				.stream().map(UserDTO::mapToUserDTO).collect(Collectors.toList());
	}

	/**
	 * Adding new user to list.
	 * 
	 * @param userData
	 * @return
	 */
	@Override
	public UserDTO addNewUserEntry(UserDTO userData) {

		if(this.userRepository.existsByMobileNumber(userData.getMobileNumber()))	{
			throw new UserAlreadExistsException(userData.getMobileNumber());
		}
		
		User user = this.userRepository.saveAndFlush((User) ModelMapperUtil.map(userData, User.class));
		
		return (UserDTO) ModelMapperUtil.map(user, UserDTO.class);
	}


	/**
	 * Updating existing user by user id
	 * 
	 * @param id
	 * @param userData
	 * @return
	 */
	@Override
	public UserDTO updateExistingUser(Long id, UserDTO userData) {

		if(!this.userRepository.existsById(id))	{
			throw new UserNotFoundException(id);
		}
		
		User user = this.userRepository.findById(id).get();
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setCity(userData.getCity());
		user.setMobileNumber(userData.getMobileNumber());
		
		user = this.userRepository.saveAndFlush(user);
		
		return (UserDTO) ModelMapperUtil.map(user, UserDTO.class);
	}

	

	/**
	 * Delete user by user id and returns deleted user.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public void deleteUser(final Long id) {

		Optional<User> user = this.userRepository.findById(id);
		
		if(user.isPresent())	{
			this.userRepository.deleteById(id);
		} else {
			throw new UserNotFoundException(id);
		}
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
