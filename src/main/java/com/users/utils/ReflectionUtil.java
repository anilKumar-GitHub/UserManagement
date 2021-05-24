package com.users.utils;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

/**
 * <b>ReflectionUtil	</b> <br/> <br/>
 * 
 * Reflection utility class is used for runtime object manipulation 
 * with object properties and methods.
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Date 		: </b> 15-March-2018 12:00:00 AM <br/> <br/>
 * <b>Category 	: </b> Utility Class <br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * @see 		{@link ReflectionUtils}
 * 
 */
public class ReflectionUtil extends ReflectionUtils {

	private ReflectionUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object findField(Object obj, String fieldName)	{

		Field field = ReflectionUtils.findField(obj.getClass(), fieldName);
		boolean isAccessible = field.isAccessible();
		field.setAccessible(true);
		try {
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			field.setAccessible(isAccessible);
		}
		return null;
	}

	/**
	 * @param obj
	 * @param fieldName
	 * @param newValue
	 * @return
	 */
	public static boolean changeFieldValue(Object obj, String fieldName, Object newValue)	{

		Field field = ReflectionUtils.findField(obj.getClass(), fieldName);
		boolean isAccessible = field.isAccessible();
		boolean isChanged = false;
		try {
			field.setAccessible(true);
			field.set(obj, newValue);
			isChanged = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally	{
			field.setAccessible(isAccessible);
		}
		return isChanged;
	}
}