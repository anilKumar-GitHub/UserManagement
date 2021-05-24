package com.users.utils;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

/**
 * <b>ModelMapperUtil	</b> <br/> <br/>
 * 
 * ModelMapperUtil utility class is used for mapping 
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
public class ModelMapperUtil	{

	private static ModelMapper mapper = new ModelMapper();
	
	public static Object map(Object sourceObject, Class classRef)	{
		return mapper.map(sourceObject, classRef);
	}
}