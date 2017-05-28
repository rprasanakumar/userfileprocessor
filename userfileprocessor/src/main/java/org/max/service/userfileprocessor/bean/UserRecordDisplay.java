package org.max.service.userfileprocessor.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class UserRecordDisplay implements Comparable<UserRecordDisplay>, Serializable{

	/**
	 * IMPORTANT
	 * Do not change this Version number unless there is a real need
	 * 
	 */
	private static final long serialVersionUID = -7657736727284509557L;
	
	
	
	
	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public ArrayList<String> getName() {
		return name;
	}



	public void setName(ArrayList<String> name) {
		this.name = name;
	}



	private String color;
	private Integer count;
	private ArrayList<String> name;
	
	

	@Override
	public int compareTo(UserRecordDisplay arg0) {
		// TODO Auto-generated method stub
		
		return this.count.compareTo(arg0.count);
	}

}
