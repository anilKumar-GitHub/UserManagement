package com.users.utils;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <b>StringUtil	</b> <br/> <br/>
 * 
 * String utility class is used for handling string common operation.
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Date 		: </b> 25-Dec-2017 12:00:00 AM <br/> <br/>
 * <b>Category 	: </b> Utility Class <br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * @see 		{@link StringUtils}
 * 
 */
public class StringUtil extends StringUtils	{

	private static final String EMPTY_STRING = "";

	private StringUtil() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @param pString
	 * @return
	 */
	public static boolean isNullOrEmpty(final String pString)	{
		
		if (pString == null || pString.isBlank() || pString.isEmpty()
				/* StringUtil.EMPTY_STRING.equals(pString.trim())  // old version relevant code */ )
			return true;
		else
			return false;
	}
	
	/**
	 * @param pStringArr
	 * @return
	 */
	public static boolean isAnyNullOrEmpty(final String ...pStringArr)	{

		for(String string : pStringArr)	{
			if(StringUtil.isNullOrEmpty(string))
				return true;
		}
		return false;
	}

	/**
	 * @param pNumbericString
	 * @return
	 */
	public Integer toInteger(final String pNumbericString) {
		try{
			return new Integer(pNumbericString);
		}catch (NumberFormatException e) {	}
		return null;
	}
	
	/**
	 * @param _number
	 * @return
	 */
	public Integer toInt(final String pNumbericString) {
		try{
			return new Integer(pNumbericString).intValue();
		}catch (NumberFormatException e) {	}
		return null;
	}

	public static boolean isContains(String searchInput, String ...searchKeys) {
		
		for(String key : searchKeys){
			if(searchInput.contains(key))
				return true;
		}
		return false;
	}
	
	
	public static String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public static <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}