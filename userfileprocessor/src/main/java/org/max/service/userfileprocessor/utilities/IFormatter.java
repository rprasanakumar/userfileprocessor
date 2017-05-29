package org.max.service.userfileprocessor.utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.max.service.userfileprocessor.error.InvaidFormatException;
/** 
 * Interface for all the Basic file Formatter used in this Application
 * This is programmed in a way to extend the type for the future use
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */

public interface IFormatter {
	
	public ArrayList<LinkedHashMap<String,String>> getVaildFormat() throws InvaidFormatException;

}
