package org.max.service.userfileprocessor.service;

import java.util.List;

import org.max.service.userfileprocessor.bean.UserRecord;

/** 
 * Interface for all the Basic FileProcessing services by this application
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */


public interface IFileProcessor {
	
	public List<UserRecord> parse();

}
