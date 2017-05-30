package org.max.service.userfileprocessor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.dao.UserRecordDAO;
import org.max.service.userfileprocessor.error.InvaidFormatException;
import org.max.service.userfileprocessor.utilities.IFormatter;
import org.max.service.userfileprocessor.utilities.VaildFormatXML;


/** 
 * Service class implementing  ICommonService interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */


public class RecordCommonServiceImpl implements  ICommonService{
	final String formatPath="config/validformats.xml";
	FileHandler handler=null;
	final String logPath="/tmp/warninglog.log";
	final String message = "Invalid record column ";
	UserRecord record;
	public RecordCommonServiceImpl(UserRecord record) {
		this.record = record;
		
	}

	

	/**
	 * Sevice method for processing single line input
	 * 
	 *@return list of type  UserRecord
	 *
	 *@throws SecurityException and IOException
	 * 
	 */
	
	
	@Override
	public List<? extends Object> execute() {
		List<UserRecord> recordList = new ArrayList<UserRecord>();
		boolean isRecordValid =parseRecordValid(record);
		Logger logger = Logger.getLogger("WarningLog"); 
		try {
			handler = new FileHandler(logPath);
			if(isRecordValid) {
				UserRecordDAO recordDAO = new UserRecordDAO();
				UserRecord recordOutput= recordDAO.insertUserRecord(record);
				recordList.add(recordOutput);
				}else {

					logger.addHandler(handler);
					SimpleFormatter lf = new SimpleFormatter();  
					handler.setFormatter(lf);    
				    logger.info(message); 
			  
				}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				return recordList;
		
		
	}
	
	
	public boolean  parseRecordValid(UserRecord rec) {
		
		ArrayList <String> inputRecord = new ArrayList<String>();
		FileProcessorCSV processor = new FileProcessorCSV();
		boolean isRecordValid =false;
		if(rec!=null) {
			if((rec.getFirstName()!=null && !rec.getFirstName().isEmpty()) && 
					(rec.getFirstName()!=null && !rec.getFirstName().isEmpty())) {
				inputRecord.add(rec.getFirstName()+" "+rec.getLastName());
				
			}
			if(rec.getAddress()!=null && !rec.getAddress().isEmpty() )
				inputRecord.add(rec.getAddress());
			if(rec.getZipCode()!=null && !rec.getZipCode().isEmpty() )
				inputRecord.add(rec.getZipCode());
			if(rec.getPhoneNumber()!=null && !rec.getPhoneNumber().isEmpty() )
				inputRecord.add(rec.getPhoneNumber());
			if(rec.getColor()!=null && !rec.getColor().isEmpty() )
				inputRecord.add(rec.getColor());
			
		}

		String[] record = inputRecord.toArray(new String[inputRecord.size()]);
		
		String[] recordFormat= processor.getLineFormat(record);

		IFormatter formatter = new VaildFormatXML(formatPath);
		try {
			ArrayList<LinkedHashMap<String,String>>formats= formatter.getVaildFormat();
			isRecordValid= processor.isLineFormatVaildate(recordFormat,formats);
			
		} catch (InvaidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return isRecordValid;
		
	}

}
