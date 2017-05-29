package org.max.service.userfileprocessor.bean;

import java.io.Serializable;
import java.util.ArrayList;


/** 
 * @author Prasanna Kumar
 * @version 0.0.1
 * POJO class to hold user display variables
 */


public class UserRecordDisplay implements Comparable<UserRecordDisplay>, Serializable{

	/**
	 * IMPORTANT
	 * Do not change this Version number unless there is a real need
	 * 
	 */
	private static final long serialVersionUID = -7657736727284509557L;
	
	
	private String color;
	private Long count;
	private ArrayList<String> name;
	
	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public Long getCount() {
		return count;
	}



	public void setCount(Long count) {
		this.count = count;
	}



	public ArrayList<String> getName() {
		return name;
	}



	public void setName(ArrayList<String> name) {
		this.name = name;
	}




	
	

	@Override
	public int compareTo(UserRecordDisplay arg0) {
		// TODO Auto-generated method stub
		
		return arg0.count.compareTo(this.count);
	}

}
