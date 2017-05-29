package org.max.service.userfileprocessor.bean;

import java.util.List;
/** 
 * @author Prasanna Kumar
 * @version 0.0.1
 * POJO class to hold venues from third party API
 */

public class UserVenue {
	
	private List<String> places;
	private String query;
	public List<String> getPlaces() {
		return places;
	}
	public void setPlaces(List<String> places) {
		this.places = places;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
	
	
	

}
