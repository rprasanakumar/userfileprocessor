package org.max.service.userfileprocessor.utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.max.service.userfileprocessor.error.InvaidFormatException;

public interface IFormatter {
	
	public ArrayList<LinkedHashMap<String,String>> getVaildFormat() throws InvaidFormatException;

}
