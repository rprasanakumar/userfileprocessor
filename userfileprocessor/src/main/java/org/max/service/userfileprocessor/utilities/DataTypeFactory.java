package org.max.service.userfileprocessor.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * Factory class for dynamically deducing the DataType of each column of each record
 * 
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */


public class DataTypeFactory {
	
	IDataType data;
	String field;
	
	
	public DataTypeFactory(String field) {
		
		this.field = field;
	}
	
	
	/**
	 * Sevice method for getting the Data type of the record
	 * 
	 * Null type is returned if not Data type matches
	 * Uses Regular Expressions to find the Data type
	 * 
	 *@param user query
	 * 
	 *@return list of type  UserRecordDisplay
	 * 
	 */
public IDataType findFieldDataType(){
	
	if(field!=null && !field.trim().isEmpty() ){
		field = field.trim();
		if(isString(field)){
			return new DataTypeString();
		}else if(isIntegerSpecial(field)){
			return new DataTypeIntegerSpecial();
		}else if(isStringSpecial(field)){
			return new DataTypeStringSpecial();
		}else if(isStringNumeric(field)){
			return new DataTypeStringNumberic();
		}
		
	}
	return new DataTypeNull();

	

}
	
	private boolean isString(String field) {
		String regExString =  "^[a-zA-Z ]*$";
		
		if(field.matches(regExString)) {
			return true;
		}
		
		return false;
	}
	
	private boolean isStringNumeric(String field) {
		//String regExStringNumeric =  "^[a-zA-Z0-9 ]*$";
		String regExStringNumeric ="^[a-zA-Z0-9 ]*$";
		Pattern pat =  Pattern.compile(regExStringNumeric);
		Matcher matcher = pat.matcher(field);
		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}
	

	private boolean isStringSpecial(String field) {
		String regExStringSpecialSpaceNDash = "\\d{3}[-\\s]\\d{3}[-\\s]\\d{4}";
		String regExStringSpecialBrace ="\\(\\d{3}\\)-\\d{3}-\\d{4}";
		
		if(field.matches(regExStringSpecialSpaceNDash) || field.matches(regExStringSpecialBrace)) {
			return true;
		}
		
		return false;
	}
	
	private boolean isIntegerSpecial(String field) {
		String regExIntegerDash = "^[0-9]{5}(?:-[0-9]{4})?$";
		if(field.matches(regExIntegerDash) ) {
			return true;
		}
		
		return false;
	}

}
