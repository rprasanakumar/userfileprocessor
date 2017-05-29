package org.max.service.userfileprocessor.service;


import java.util.List;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;


/** 
 * Interface for all the Basic Data Display services by this application
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */

public interface IUserDataDisplay {
	
	public   List<? extends Object> pullData(String query);

}
