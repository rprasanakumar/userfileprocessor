package org.max.service.userfileprocessor.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTypeFactory {
	
	IDataType data;
	String field;
	

	final String regExInteger = "^[0-9]*$";
	
	public DataTypeFactory(String field) {
		
		this.field = field;
	}
	
public IDataType findFieldDataType(){
	
	if(field!=null && !field.trim().isEmpty() ){
		field = field.replaceAll("^\"|\"$", "");
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
