package com.users.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.users.model.dto.AbstractDTO;


/**
 * <b>RestResponseUtil	</b> <br/> <br/>
 * 
 * Reflection utility class is used for creating
 * custom generic rest response entity along with
 * HTTP response status.
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Date 		: </b> 11-Dec-2017 12:00:00 AM <br/> <br/>
 * <b>Category 	: </b> Utility Class <br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * @see 		{@link ResponseEntity}
 * 
 */
public class RestResponseUtil {

	public final static String REST_RESPONSE_TYPE_CUSTOUM = "REST_CUSTOUM_RESPONSE";
	
	public final static String REST_RESPONSE_ERROR_MESSAGE = "CUSTOUM_MESSAGE";
	
	public final static String REST_RESPONSE_ERRORS_LIST = "ERRORS_LIST";
	
	public final static String REST_RESPONSE_ERRORS_MAP = "ERRORS_MAP";

	/**
	 * Default constructor
	 * Private constructor
	 */
	private RestResponseUtil() {
		throw new UnsupportedOperationException(
				ResponseEntity.class.getSimpleName()
					+ " is an utill class, can't instantiated.");
	}
	
	/**
	 * {@link ResponseEntity} class method for {@link ResponseEntity#ResponseEntity(HttpStatus)}
	 * 
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity responseEntity(HttpStatus httpStatus) {
		return new ResponseEntity(httpStatus);
	}

	/**
	 * {@link ResponseEntity} class method for {@link ResponseEntity#ResponseEntity(HttpStatus)}
	 * 
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity responseEntity(Object body) {
		return new ResponseEntity(body, HttpStatus.OK);
	}

	/**
	 * {@link ResponseEntity} class method for {@link ResponseEntity#ResponseEntity(Object, HttpStatus)}
	 * 
	 * @param body
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity responseEntity(Object body, HttpStatus httpStatus) {
		return new ResponseEntity(body, httpStatus);
	}

	/**
	 * {@link ResponseEntity} class method for {@link ResponseEntity#ResponseEntity(MultiValueMap, HttpStatus)}
	 * 
	 * @param headers
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity responseEntity(MultiValueMap<String, String> headers, HttpStatus httpStatus) {
		return new ResponseEntity(headers, httpStatus);
	}

	/**
	 * {@link ResponseEntity} class method for {@link ResponseEntity#ResponseEntity(Object, MultiValueMap, HttpStatus)}
	 * 
	 * @param body
	 * @param headers
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity responseEntity(Object body, MultiValueMap<String, String> headers, HttpStatus httpStatus) {
		return new ResponseEntity(body, headers, httpStatus);
	}


	/**
	 * Custom Rest Error Response for single error message with default {@link HttpStatus#BAD_REQUEST} response.
	 * 
	 * @param defaultMessage
	 * @return
	 */
	public static ResponseEntity badResponseEntity(String defaultMessage) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("errors_type", RestResponseUtil.REST_RESPONSE_ERROR_MESSAGE);
		response.put("errors", defaultMessage);
		return RestResponseUtil.finalResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Custom Rest Error Response for single error message.
	 * 
	 * @param defaultMessage
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity badResponseEntity(String defaultMessage, HttpStatus httpStatus) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("errors_type", RestResponseUtil.REST_RESPONSE_ERROR_MESSAGE);
		response.put("errors", defaultMessage);
		return RestResponseUtil.finalResponseEntity(response, httpStatus);
	}

	/**
	 * Custom Rest Error Response for {@link List} of error message with default {@link HttpStatus#BAD_REQUEST} response.
	 * 
	 * @param defaultMessages
	 * @return
	 */
	public static ResponseEntity badResponseEntity(List<String> defaultMessages) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("errors_type", RestResponseUtil.REST_RESPONSE_ERRORS_LIST);
		response.put("errors", defaultMessages);		
		return RestResponseUtil.finalResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Custom Rest Error Response for {@link List} of error message.
	 * 
	 * @param defaultMessages
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity badResponseEntity(List<String> defaultMessages, HttpStatus httpStatus) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("errors_type", RestResponseUtil.REST_RESPONSE_ERRORS_LIST);
		response.put("errors", defaultMessages);		
		return RestResponseUtil.finalResponseEntity(response, httpStatus);
	}


	/**
	 * Custom Rest Error Response for {@link Map} of error message having 
	 * error fields as key and error messages as description <br/>
	 * with default {@link HttpStatus#BAD_REQUEST} response.
	 * 
	 * @param errorsKeyValueMap
	 * @return
	 */
	public static ResponseEntity badResponseEntity(Map<String, String> errorsKeyValueMap) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("errors_type", RestResponseUtil.REST_RESPONSE_ERRORS_MAP);
		response.put("errors", errorsKeyValueMap);		
		return RestResponseUtil.finalResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Custom Rest Error Response for {@link Map} of error message having 
	 * error fields as key and error messages as description.
	 * 
	 * @param errorsKeyValueMap
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity badResponseEntity(Map<String, String> errorsKeyValueMap, HttpStatus httpStatus) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("errors_type", RestResponseUtil.REST_RESPONSE_ERRORS_MAP);
		response.put("errors", errorsKeyValueMap);		
		return RestResponseUtil.finalResponseEntity(response, httpStatus);
	}

	/**
	 * Custom Rest Error Response for single error message
	 * with default {@link HttpStatus#BAD_REQUEST} response.
	 * 
	 * @param defaultMessage
	 * @return
	 */
	public static ResponseEntity badResponseEntity(AbstractDTO dto) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> errorsList = (List<String>) ReflectionUtil.findField(dto, "errorsList");
		Map<String, String> errorsMap = (Map<String, String>) ReflectionUtil.findField(dto, "errorsMap");
		
		RestErrorResponse errorResponse = new RestErrorResponse(HttpStatus.BAD_REQUEST);
		
		if(!CollectionUtil.isNullOrEmpty(errorsList))	{
			errorResponse.setMessages(errorsList);
		}
		if(!CollectionUtil.isNullOrEmpty(errorsMap))	{
			errorsMap.entrySet().parallelStream()
			.filter(entry -> !StringUtil.isNullOrEmpty(entry.getKey()))
			.forEach(entry -> errorResponse.setError(dto.getClass()
				.getSimpleName(), entry.getKey(), entry.getValue()));
		}
		return RestResponseUtil.responseEntity(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Common method for creating final {@link ResponseEntity} instance 
	 * 
	 * @param responseMap
	 * @param httpStatus
	 * @return
	 */
	private static ResponseEntity finalResponseEntity(Map<String, Object> responseMap, HttpStatus httpStatus)	{

		RestErrorResponse errorResponse = new RestErrorResponse(httpStatus);
		String errorsType = (String) responseMap.get("errors_type");
		if(RestResponseUtil.REST_RESPONSE_ERROR_MESSAGE.equals(errorsType))	{
			errorResponse.put((String) responseMap.get("errors"));
		}
		if(RestResponseUtil.REST_RESPONSE_ERRORS_LIST.equals(errorsType))	{
			errorResponse.setMessages((List<String>) responseMap.get("errors"));
		}
		if(RestResponseUtil.REST_RESPONSE_ERRORS_MAP.equals(errorsType))	{
			Map<String, String> errorsKeyValueMap = (Map<String, String>) responseMap.get("errors");
			errorsKeyValueMap.entrySet().parallelStream()
			.filter(entry -> !StringUtil.isNullOrEmpty(entry.getKey()))
			.forEach(entry -> errorResponse.setError(null, entry.getKey(), entry.getValue()));
		}
		
		return new ResponseEntity(errorResponse, httpStatus);
	}
	
	/**
	 * Validation error response handler thrown by validation classes.
	 * 
	 * @param objectErrors
	 * @return
	 */
	public static ResponseEntity onValidationFailure(List<ObjectError> objectErrors)	{

		RestErrorResponse errorResponse = new RestErrorResponse(HttpStatus.BAD_REQUEST);
		
		for(ObjectError objectError : objectErrors)	{
			if(objectError instanceof FieldError)	{
				FieldError fieldError = (FieldError) objectError;
				errorResponse.setError(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
			} else {
				if(StringUtil.isNullOrEmpty(objectError.getDefaultMessage()))	{
					errorResponse.put(objectError.getCode());
				} else {
					errorResponse.setError(objectError.getObjectName(), objectError.getCode(), objectError.getDefaultMessage());
				}
			}
		}
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
	}
}