package com.users.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

/**
 * <b>RestErrorResponse	</b> <br/> <br/>
 * 
 * Rest Error Response class is used for sending validation failed 
 * or condition non-satisfied errors in json format.
 * Rest Error Response contains generic error messages and property based error messages.
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Date 		: </b> 21-Nov-2018 12:00:00 AM <br/> <br/>
 * <b>Category 	: </b> Entity Class <br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * @see			{@link ServletResponse}, {@link HttpServletResponse}, {@link Errors}
 * 
 */
public class RestErrorResponse	{
	
	private long timestamp;
	
	private HttpStatus status;
	
	private String statusText;

	private List<String> messages;
	
	/**
	 * <b>Errors	</b> <br/> <br/>
	 * 
	 * Error class is used for binding property based validation messages. 
	 * 
	 * <br/> <br/> <br/>
	 * 
	 * <b>Date 		: </b> 21-Nov-2018 12:00:00 AM <br/> <br/>
	 * <b>Category 	: </b> Entity Class <br/> <br/>
	 * 
	 * @version		1.0
	 * @author 		Anil Kumar
	 * @see			{@link RestErrorResponse}
	 * 
	 */
	public class Errors	{
		
		public String objcet;
		public String field; 
		public String message;

		public Errors(String objcet, String field, String message)	{
			this.objcet = objcet;
			this.field = field;
			this.message = message;
		}
	}
	
	private List<Errors> errors;

	
	/**
	 * @param badRequest
	 */
	public RestErrorResponse(HttpStatus badRequest) {
		this.status = badRequest;
		this.statusText = badRequest.toString();
		this.timestamp = System.currentTimeMillis();
		this.messages = new ArrayList<String>();
		this.errors = new ArrayList<Errors>();
	}
	
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}
	
	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @param messages
	 */
	public void put(String messages) {
		this.messages.add(messages);
	}

	/**
	 * @param propertyName
	 * @param errorMessage
	 */
	public void setError(String objcet, String field, String message) {
		this.errors.add(new Errors(objcet, field, message));
	}
	
	/**
	 * @return
	 */
	public List<Errors> getErrors()	{
		return this.errors;
	}

	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}
}
