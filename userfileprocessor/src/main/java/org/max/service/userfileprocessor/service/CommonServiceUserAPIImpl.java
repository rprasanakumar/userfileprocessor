package org.max.service.userfileprocessor.service;

import java.util.ArrayList;
import java.util.List;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.bean.UserVenue;


/** 
 * Service class implementing  ICommonService interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */



public class CommonServiceUserAPIImpl implements ICommonService{
	
	String query;
	IUserDataDisplay displayType;

	public CommonServiceUserAPIImpl(IUserDataDisplay displayType,String query) {
		this.displayType = displayType;
		this.query = query;
	}
	
	

	/**
	 * Sevice method for invoking helper method for 
	 * pulling required data from the DB
	 * 
	 *@return list of type  String
	 * 
	 */

	@Override
	public List<? extends Object> execute() {
		List<UserVenue>  listVenue = new ArrayList<UserVenue>(); 
		UserVenue uservenue = new UserVenue();
		ArrayList<String> out= (ArrayList<String>) this.displayType.pullData(query);
		uservenue.setPlaces(out);
		listVenue.add(uservenue);
		return listVenue ;
	}
	

}
