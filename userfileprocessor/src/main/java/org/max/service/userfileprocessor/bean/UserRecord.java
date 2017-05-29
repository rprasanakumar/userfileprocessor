package org.max.service.userfileprocessor.bean;

import java.io.Serializable;



/** 
 * @author Prasanna Kumar
 * @version 0.0.1
 * POJO class to hold user record
 */

public class UserRecord implements Comparable<UserRecord>, Serializable {
	
	/**
	 * IMPORTANT
	 * Do not change this Version number unless there is a real need
	 * 
	 */
	private static final long serialVersionUID = -6305455888154930616L;
	
	
	
private String iD;
private String firstName;
private String lastName;
private String name;
private String address;
private String zipCode;
private String phoneNumber;
private String color;
private String path;
private String lat;
	
	
	

public String getiD() {
	return iD;
}



public void setiD(String iD) {
	this.iD = iD;
}

public String getFirstName() {
	return firstName;
}


private void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


private void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getAddress() {
	return address;
}


private void setAddress(String address) {
	this.address = address;
}


public String getZipCode() {
	return zipCode;
}


private void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}


public String getPhoneNumber() {
	return phoneNumber;
}


private void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}


public String getColor() {
	return color;
}


private void setColor(String color) {
	this.color = color;
}


public String getName() {
	return this.getFirstName()+" "+this.getLastName();
}


private void setName(String name) {
	this.name = name;
	
}

public String getPath() {
	return path;
}



public void setPath(String path) {
	this.path = path;
}

public String getLat() {
	return lat;
}



public void setLat(String lat) {
	this.lat = lat;
}





/** IMPORTANT 
 *setUser factory method.
 *Please include the conditional logic for any new  field you are adding here
	
	*/ 

public void setUser(String field, String value) {
	
	if("firstName".equals(field)) {
		
		this.setFirstName(value);
		
	}else if("lastName".equals(field)){
		this.setLastName(value);
		
	}else if("name".equals(field)){
		this.setName(value);
		if(value!=null) {
			String[] nameSplit = value.split("\\s+");
			int size = nameSplit.length;
			
			if(size==1) {
				setFirstName(nameSplit[size-1]);
				
			}else if(size>1){
				String lastName= nameSplit[size-1];
				String firstName = value.substring(0, value.length()-nameSplit[size-1].length()-1);
				
				setLastName(lastName);
				setFirstName(firstName);
			}
			
		}
			
		
	}else if("address".equals(field)){
		this.setAddress(value);
		
	}else if("zipCode".equals(field)){
		this.setZipCode(value);
		
	}else if("phoneNumber".equals(field)){
		this.setPhoneNumber(value);
		
	}else if("color".equals(field)){
		this.setColor(value);
		
	}
	
	
}


public int compareTo(UserRecord o) {
	return this.getLastName().compareTo(o.getLastName());
}








}
