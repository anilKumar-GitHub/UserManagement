package com.users.model.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>AbstractDTO	</b> <br/> <br/>
 * 
 * AbstractDTO provides common functionalities among multiple dto's.<br/>
 * Also provides two special futures.
 * <ol>
 * 	<li>Handling error mechanism using {@link AbstractDTO#hasErrors()} 
 * 		and {@link AbstractDTO#err(String)}/{@link AbstractDTO#err(String, String)} for placing any runtime errors.</li>
 * <li>Object transformation among multiple method calls using 
 * 		{@link AbstractDTO#set(String, Object)} and {@link AbstractDTO#get(String)} methods.</li>
 * </ol>
 * 
 * <br/> <br/> <br/>
 * 
 * <b>Date 		: </b> 15-Jan-2018 12:00:00 AM <br/> <br/>
 * <b>Category 	: </b> Data Transfer Objects <br/> <br/>
 * 
 * @version		1.0
 * @author 		Anil Kumar
 * 
 */
public abstract class AbstractDTO  implements Comparable<AbstractDTO>	{
	
	protected Long id;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List<String> errorsList = new ArrayList<String>();
	
	private Map<String, String> errorsMap = new HashMap<String, String>();

	private String action;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		if(id == null)	{
			return System.currentTimeMillis();			
		}
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public Object set(String key, Object value)	{
		if(key == null)	return null;
		if(value == null)	{
			if(dataMap.containsKey(key))
				dataMap.remove(key);
			return null;
		}
		return dataMap.put(key, value);
	}

	/**
	 * @param key
	 * @return
	 */
	public Object get(String key)	{
		if(key == null)	return null;
		return dataMap.get(key);
	}
	
	/**
	 * @param errorMessage
	 */
	public void err(String errorMessage)	{
		if(errorMessage != null)	{
			errorsList.add(errorMessage);
		}
	}

	/**
	 * @param errorKey
	 * @param errorMessage
	 */
	public void err(String errorKey, String errorMessage)	{
		if(errorKey != null && errorMessage != null)	{
			errorsMap.put(errorKey, errorMessage);
		}
	}

	/**
	 * @return
	 */
	public boolean hasErrors()	{
		return !(errorsList.isEmpty() && errorsMap.isEmpty());
	}

	public Map getData()	{
		return this.dataMap;
	}
	
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * 	default implementation of {@link Object#equals(Object)} method for dto's.
	 * 
	 * 	@param obj
	 *	@return boolean
	 */
	@Override
	public boolean equals(Object obj) {

		if(obj == null || this.id == null)	return false;
		
		if(!(obj instanceof AbstractDTO))	return false;
		
		AbstractDTO dto = (AbstractDTO) obj;
		
		return this == obj || this.id.equals(dto.id);
	}

	/**
	 *	default implementation of {@link Object#hashCode()} based on id of dto's.
	 * 
	 *  @return integer
	 */
	@Override
	public int hashCode() {
		int res = 17;
		return 31 * res + this.id.hashCode();
	}

	/**
	 * 	default implementation of {@link Comparable#compareTo(Object)} for dto level comparison.
	 * 
	 * 	@param dto
	 * 	@return integer
	 */
	@Override
	public int compareTo(AbstractDTO dto) {
		return this.id.compareTo(dto.id);
	}
}