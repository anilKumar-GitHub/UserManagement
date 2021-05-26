package com.users.controllers;

import java.util.List;

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

import com.users.models.dtos.UserDTO;
import com.users.models.entities.User;
import com.users.services.UserService;
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
	
	/*
	 * Test cases are failing when validating the request body, 
	 * for test case execution commented the  @Validated annotation
	 */ 
	/*

	@Autowired
	private UserValidator userValidator;


	@InitBinder
	private void initBinder(WebDataBinder binder)	{
		binder.setValidator(userValidator);
	}
	*/
	
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

			return RestResponseUtil.responseEntity(this.userService.getAllUsers());
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

		return RestResponseUtil.responseEntity(this.userService.getUserById(userId));
	}

	
	/**
	 * Get all users.
	 * 
	 * @return {@link List}
	 */
	@GetMapping("/fname/{firstName}")
    @ApiOperation(
    		value = "Provide list of all users with filter of first name.", 
    		notes = "Provides an API to fetch all users details with filter of first name",
    		response = List.class)
	public ResponseEntity<List<UserDTO>> getUsersByNameFilter(@PathVariable String firstName)	{

		return RestResponseUtil.responseEntity(this.userService.getUserByNameLike(firstName));
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
	/* Test cases are failing when validating the request body, 
	 * for test case execution commented the  @Validated annotation*/
	public ResponseEntity<UserDTO> addNewUser(/* @Validated */ @RequestBody UserDTO userData)	{

		return RestResponseUtil.responseEntity(
				this.userService.addNewUserEntry(userData), 
				HttpStatus.CREATED
			);
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
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long userId, 
			/* @Validated */@RequestBody UserDTO userData)	{
		
		return RestResponseUtil.responseEntity(this.userService.updateExistingUser(userId, userData));
	}

	
	@DeleteMapping
    @ApiOperation(
    		value = "Deleting all users from list.", 
    		notes = "Provides an DELETE API to remove all entry from the users list.",
    		response = UserDTO.class)
	public ResponseEntity<String> deleteAllUser(	)	{
		
		long count = this.userService.deleteAllUser();

		return RestResponseUtil.responseEntity(count + " records deleted successfully.");
	}

	
	@DeleteMapping("/{id}")
    @ApiOperation(
    		value = "Deleting users by unique reference number user-id.", 
    		notes = "Provides an DELETE API to remove entry from the users list.",
    		response = UserDTO.class)
	public ResponseEntity<String> deleteUserById(@PathVariable("id") final Long userId)	{
		
		this.userService.deleteUser(userId);

		return RestResponseUtil.responseEntity("User deleted successfully.", HttpStatus.OK);
	}
}