package org.max.service.userfileprocessor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.dao.UserRecordDAO;
/** 
 * Service class implementing  IUserDataDisplay interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */

public class UserDataDisplayColor implements IUserDataDisplay{

	
	
	/**
	 * Sevice method for connecting to DAO layer to pull user record to display
	 * 
	 *@param user query
	 * 
	 *@return list of type  UserRecordDisplay
	 * 
	 */
	@Override
	public List<UserRecordDisplay> pullData(String query) {

		List<UserRecordDisplay> resultList = new ArrayList<UserRecordDisplay>();
		UserRecordDisplay userData; 
		UserRecordDAO dao = new UserRecordDAO(); 
		Map<String,Map<String,Long>> recordMap = dao.selectUserRecordColorNCount();
		for(String key:recordMap.keySet()) {
			userData= new UserRecordDisplay();
			userData.setColor(key);
			userData.setCount(recordMap.get(key).get("value"));
			resultList.add(userData);
		}
		
		Collections.sort(resultList);
		return resultList;
	
		
	}
	
	
	
	

}
