package com.users.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.users.utils.RestResponseUtil;


/**
 * <b>GlobalExceptionHandler		</b> <br/> <br/>
 * 
 * Exception handling using Spring AOP Advice and forming rest error response entity 
 * for all type of exception that raise in application.
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Category 	: </b> Exception Handler <br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * @see 		{@link UserAlreadExistsException}, {@link UserNotFoundException}
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * <b>ResourceNotFoundException</b> is thrown whenever any resource/objects not found.
	 * 
	 * @param request		{@link HttpServletRequest}
	 * @param exception		{@link UserNotFoundException}
	 * @return
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity handleUserNotFoundException(HttpServletRequest request, UserNotFoundException exception)	{
		
		log.info("@ResourceNotFoundException : " + exception.getMessage());
		
		return RestResponseUtil.badResponseEntity(exception.getMessage());
	}


	/**
	 * <b>RuntimeException</b> is caught if any runtime exception is thrown from application.
	 * 
	 * @param request		{@link HttpServletRequest}
	 * @param exception		{@link Exception}
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity handleRuntimeException(HttpServletRequest request, RuntimeException exception)	{
		log.info("@RuntimeException : " + exception.getMessage());
		exception.printStackTrace();
		return RestResponseUtil.badResponseEntity(exception.getMessage());
	}	
}