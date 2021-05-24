package com.users.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <b>ResourceNotFoundException		</b> <br/> <br/>
 * 
 * Resource not found exception handler for if resource is not found.
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Category 	: </b> Exception Handler	<br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * 
 */
public class UserNotFoundException extends RuntimeException		{
	
	private Logger log = LoggerFactory.getLogger(UserNotFoundException.class);

	private static final long serialVersionUID = 1L;
		
	/**
	 * Default constructor, 
	 * 
	 */
	public UserNotFoundException() {
		super();
	}
	
	/**
	 * 
	 * @param exceptionMessage	{@link String}
	 */
	public UserNotFoundException(final Long id) {
		super("User with id " + id + " is not found");
	}	
}