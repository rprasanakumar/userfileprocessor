package org.max.service.userfileprocessor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.bean.UserVenue;
import org.max.service.userfileprocessor.dao.UserRecordDAO;


/** 
 * Service class implementing  IUserDataDisplay interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */

public class UserDataDisplayAPI implements IUserDataDisplay{
	
	
	/**
	 * Sevice method for connecting to DAO layer to pull the Venues from the API
	 * 
	 *@param user query
	 * 
	 *@return list of type  String
	 * 
	 */

	@Override
	public List<String> pullData(String query) {

		List<String> resultList ;
		UserRecordDAO dao = new UserRecordDAO(); 
		resultList = dao.searchUserVenue(query);
		Collections.sort(resultList);
		return resultList;
	
		
	
	}

}
