package com.users.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.exceptions.UserNotFoundException;
import com.users.model.dtos.UserDTO;
import com.users.model.entities.User;
import com.users.services.UserService;
import com.users.utils.ModelMapperUtil;
import com.users.utils.RestResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * REST end-point for user management and CRUD operation demo.
 * 
 * @author anilKumar
 * 
 * @see UserService
 *
 */
@RestController
@RequestMapping("/users")
@Api(value = "UsersCtrl", description = "Users API endpoints for CURD operation.")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Get all users.
	 * 
	 * @return {@link List}
	 */
	@GetMapping
    @ApiOperation(
    		value = "Provide list of all users.", 
    		notes = "Provides an API to fetch all users details",
    		response = List.class)
	public ResponseEntity<List<UserDTO>> getAllUsers()	{

		List<User> users = this.userService.getAllUsers();

		List<UserDTO> respose = users.stream().map(UserDTO::mapToUserDTO).collect(Collectors.toList());
		
		return RestResponseUtil.responseEntity(respose, HttpStatus.OK);
	}
	
	
	/**
	 * Fetch user by id.
	 * 
	 * @param userId
	 * @return User
	 */
	@GetMapping("/{id}")
    @ApiOperation(
    		value = "Fetch perticular User by id.", 
    		notes = "Provides an API to fetch user details by id.",
    		response = UserDTO.class)
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long userId)	{

		User user = this.userService.getUserById(userId);
		
		if(user == null)	{
			throw new UserNotFoundException(userId);
		}

		return RestResponseUtil.responseEntity(UserDTO.mapToUserDTO(user), HttpStatus.OK);
	}
	

	/**
	 * Adding new user entry to users list.
	 * 
	 * @param userData
	 * @return {@link User}
	 */
	@PostMapping
    @ApiOperation(
    		value = "Adding new user entry to Users list.", 
    		notes = "Provides an POST API to add entry to users list.",
    		response = UserDTO.class)
	public ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO userData)	{

		User user = this.userService.addNewUserEntry(userData);

		return RestResponseUtil.responseEntity(ModelMapperUtil
			.map(user, UserDTO.class), HttpStatus.CREATED);
	}
	
	/**
	 * Update the existing user by use-id.
	 * 
	 * @param userId
	 * @param userData
	 * @return
	 */
	@PutMapping("/{id}")
    @ApiOperation(
    		value = "Updating existing user by unique reference number user-id..", 
    		notes = "Provides an PUT API to update entry from the users list.",
    		response = UserDTO.class)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long userId, @RequestBody UserDTO userData)	{
		
		User user = this.userService.updateExistingUser(userId, userData);
		
		UserDTO userDto = (UserDTO) ModelMapperUtil.map(user, UserDTO.class);
		
		return RestResponseUtil.responseEntity(userDto, HttpStatus.OK);
	}

	
	@DeleteMapping
    @ApiOperation(
    		value = "Deleting all users from list.", 
    		notes = "Provides an DELETE API to remove all entry from the users list.",
    		response = UserDTO.class)
	public ResponseEntity<String> deleteAllUser(	)	{
		
		long count = this.userService.deleteAllUser();

		return RestResponseUtil.responseEntity(count + " records deleted successfully.",HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
    @ApiOperation(
    		value = "Deleting users by unique reference number user-id.", 
    		notes = "Provides an DELETE API to remove entry from the users list.",
    		response = UserDTO.class)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long userId)	{
		
		User user = this.userService.deleteUser(userId);
		
		UserDTO userDto = (UserDTO) ModelMapperUtil.map(user, UserDTO.class);
		
		return RestResponseUtil.responseEntity(userDto, HttpStatus.OK);
	}
}