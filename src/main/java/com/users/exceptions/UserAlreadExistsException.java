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
 * @see			{@link BusinessFlowException}, {@link GlobalExceptionHandler}
 * 
 */
public class UserAlreadExistsException extends RuntimeException		{
	
	private Logger log = LoggerFactory.getLogger(UserAlreadExistsException.class);

	private static final long serialVersionUID = 1L;
		
	/**
	 * Default constructor, 
	 * 
	 */
	public UserAlreadExistsException() {
		super("User alread exists.");
	}
	
	/**
	 * 
	 * @param exceptionMessage	{@link String}
	 */
	public UserAlreadExistsException(final String mobileNumber) {
		super("User alread exists with same mobile number : " + mobileNumber + ".");
	}	
	}