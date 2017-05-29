package org.max.service.userfileprocessor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.dao.UserRecordDAO;
/** 
 * Service class implementing  IUserDataDisplay interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */

public class UserDataDisplayColorName implements IUserDataDisplay{
	
	
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

		
		UserRecordDAO dao = new UserRecordDAO(); 
		List<UserRecord> listUserRecord = dao.selectUserRecord();
		ArrayList<UserRecordDisplay> result = new ArrayList<UserRecordDisplay>();
		UserRecordDisplay userData; 
		HashMap<String,ArrayList<String>> userColorMap = new HashMap<String,ArrayList<String>>();
		for(UserRecord list :listUserRecord) {
			String key = list.getColor();
			if(userColorMap.containsKey(key)) {
				
				ArrayList<String> valueList = userColorMap.get(key);
				valueList.add(list.getName());
				userColorMap.put(key, valueList);
			}else {
				ArrayList<String> valueList = new ArrayList<String>();
				valueList.add(list.getName());
				userColorMap.put(key, valueList);
				
			}
			
		}
		
		Set<Entry<String,ArrayList<String>>> set = userColorMap.entrySet();
		List<Entry<String,ArrayList<String>>> list = new ArrayList<Entry<String,ArrayList<String>>>(set); 
		
		Collections.sort(list, new Comparator<Map.Entry<String,ArrayList<String>>>(){

		
			public int compare(Map.Entry<String, ArrayList<String>> o1, Map.Entry<String, ArrayList<String>> o2) {
				// TODO Auto-generated method stub
				return ((Integer)o2.getValue().size()).compareTo((Integer)o1.getValue().size());
			}
			
		});
		for(String key:userColorMap.keySet()) {
			userData= new UserRecordDisplay();
			ArrayList<String> valueSet = userColorMap.get(key);
			Collections.sort(valueSet);
			Collections.reverse(valueSet);
			userData.setColor(key);
			userData.setCount((Long.valueOf(valueSet.size())));
			userData.setName(valueSet);
			result.add(userData);
					
		}
		
		return result;

	}

}
