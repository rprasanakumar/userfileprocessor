package org.max.service.userfileprocessor.service;

import java.util.List;
import java.util.Map;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.dao.UserRecordDAO;


/** 
 * Service class implementing  ICommonService interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */



public class CommonServiceUserDataImpl implements ICommonService{
	
	IUserDataDisplay displayType;
	String query;
	public CommonServiceUserDataImpl(IUserDataDisplay displayType,String query) {
		this.displayType = displayType;
		this.query = query;
	}
	
	
	
	/**
	 * Sevice method for invoking helper method for 
	 * pulling required data from the DB
	 * 
	 *@return list of type  UserRecordDisplay
	 * 
	 */

	@Override
	public List<UserRecordDisplay> execute() {

		return (List<UserRecordDisplay>) this.displayType.pullData(query);
	}

}
