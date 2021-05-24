package com.users.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <b>CollectionUtil	</b> <br/> <br/>
 * 
 * Collection utility class for handling common collection related operations.
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Date 		: </b> 15-March-2018 12:00:00 AM <br/> <br/>
 * <b>Category 	: </b> Utility Class <br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * 
 */
public class CollectionUtil {

	private CollectionUtil() {
		throw new UnsupportedOperationException();
	}
	
	public static List EMPTY_LIST = new ArrayList();
	
	/**
	 * @param _obj
	 * @return
	 */
	public static boolean isNullOrEmpty(final Collection _obj)	{
		
		if( _obj == null || _obj.isEmpty() )
			return true;
		else
			return false;
	}
	
	/**
	 * @param _obj
	 * @return
	 */
	public static boolean isNullOrEmpty(final Map _obj) {
		if( _obj == null || _obj.isEmpty() )
			return true;
		else
			return false;		
	}
	
	/**
	 * @param _obj
	 * @return
	 */
	public static boolean isAnyNullOrEmpty(final Collection ..._obj)	{
		
		for(Collection obj : _obj)	{
			if(CollectionUtil.isNullOrEmpty(obj))	return true;
		}
		return false;
	}
}