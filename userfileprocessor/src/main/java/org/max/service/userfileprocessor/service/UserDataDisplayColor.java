package org.max.service.userfileprocessor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.dao.UserRecordDAO;

public class UserDataDisplayColor implements IUserDataDisplay{

	@Override
	public List<UserRecordDisplay> pullData() {

		List<UserRecordDisplay> resultList = new ArrayList<UserRecordDisplay>();
		UserRecordDisplay userData= new UserRecordDisplay(); 
		UserRecordDAO dao = new UserRecordDAO(); 
		Map<String, Integer> recordMap = dao.selectUserRecordColorNCount();
		
		for(String key:recordMap.keySet()) {
			userData.setColor(key);
			userData.setCount(recordMap.get(key));
			resultList.add(userData);
		}
		return resultList;
	
		
	}
	
	
	
	

}
