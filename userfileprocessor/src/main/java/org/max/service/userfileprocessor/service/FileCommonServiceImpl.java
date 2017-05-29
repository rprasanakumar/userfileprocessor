package org.max.service.userfileprocessor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.dao.UserRecordDAO;

/** 
 * Service class implementing  ICommonService interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */




public class FileCommonServiceImpl implements ICommonService{

	
	String path;
	IFileProcessor processor;
	UserRecordDAO dao;
	public FileCommonServiceImpl(String path) {
		
		this.path = path;
	}
	
	

	/**
	 * Sevice method for invoking helper method for 
	 * pulling required data from the DB
	 * 
	 *@return list of type  UserRecord
	 * 
	 */
	
	@Override
	public List<UserRecordDisplay> execute() {
		dao = new UserRecordDAO();
		processor = new FileProcessorCSV(this.path);
		List<UserRecord> recordList= processor.parse();
		UserRecordDisplay recordDisplay = new UserRecordDisplay();
		List<UserRecordDisplay> userRecordList = new ArrayList<UserRecordDisplay>();
		if(!recordList.isEmpty()) {
			dao.insertUserRecordFile(recordList);
		}
		recordDisplay.setCount((Long.valueOf(recordList.size())));
		userRecordList.add(recordDisplay);
		return userRecordList;
		
	}
	
	
	// Use to save file to new location
	public synchronized void writeFile(InputStream fileInputStream,
	        String fileLocation) {

	    try {
	        OutputStream out =null;
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        File file = new File(fileLocation);
	        
	        if(file.exists()) {
	        	out = new FileOutputStream(file);
	        }else {
	        	file.createNewFile();
	        	out = new FileOutputStream(file);
	        }
	        
	        
	        
	        while ((read = fileInputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e) {

	        e.printStackTrace();
	    }

	   }
	

}
