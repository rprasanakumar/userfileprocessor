package org.max.service.userfileprocessor.service;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.dao.MyBatisConnectionFactory;
import org.max.service.userfileprocessor.error.InvaidFormatException;
import org.max.service.userfileprocessor.utilities.DataTypeFactory;
import org.max.service.userfileprocessor.utilities.IFormatter;
import org.max.service.userfileprocessor.utilities.VaildFormatXML;

import au.com.bytecode.opencsv.*;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

/** 
 * Service class implementing  IFileProcessor interface
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */




public class FileProcessorCSV implements IFileProcessor{
	
	Logger logger = Logger.getLogger("WarningLog"); 
	
	String filePath;
	ArrayList<LinkedHashMap<String,String>>formats;
	int formatIndex =-1;
	final int START_READ_FROM_LINE = 1;
	/*final String formatPath= FileProcessorCSV.class.getResource("validformats.xml").getPath();
	final String logPath=FileProcessorCSV.class.getResource("warninglog.log").getPath();*/
	final String formatPath="config/validformats.xml";
	final String logPath="warninglog.log";
	final String message = "Invalid record format line# ";
	
	
	
	
	public  FileProcessorCSV(String path) {
		
		this.filePath = path;
	}

	
	/**
	 * Sevice method for parsing the CSV file
	 * 
	 * This method takes the valid format of record dynamically from the XML configuration (refer VaildFormatXML.getVaildFormat() )
	 * 
	 *@return list of type  UserRecord
	 * 
	 */
	
	
	@Override
	public List<UserRecord> parse() {
		int recordLineNumber =1;
		CSVReader csvReader = null;
		FileHandler handler=null;
		ColumnPositionMappingStrategy<UserRecord> mappingStrategy;
		List<UserRecord> recordList = new ArrayList<UserRecord>();
		UserRecord uRecordBean;
		try{
			if(filePath!=null){
				String[] records=null;
				handler = new FileHandler(logPath);
				csvReader = new CSVReader(new FileReader(filePath),',','%');
				
				// get valid format from config XML --validformats from config folder
				
				IFormatter formatter = new VaildFormatXML(formatPath);
				this.formats= formatter.getVaildFormat();
				
			      while((records = csvReader.readNext())!=null)
		            {
			    	  uRecordBean = new UserRecord();
			    	  String[] recordFormat = getLineFormat(records);
			    	  int indexRecord =0;
			    	  boolean isRecordValid = isLineFormatVaildate(recordFormat);
			    	  if(isRecordValid){
			    		  mappingStrategy = new ColumnPositionMappingStrategy<UserRecord>();
				          mappingStrategy.setType(UserRecord.class);
				            
				            //Set recordformat for mappingStrategy
				            String[] recordFieldsMap = this.formats.get(formatIndex).keySet().toArray(new String[this.formats.get(formatIndex).size()]);
				            for(String field:recordFieldsMap) {
				            	uRecordBean.setUser(field, records[indexRecord++].trim());
				            }
				            recordList.add(uRecordBean);
			    	  }
			    	  else{
			    			logger.addHandler(handler);
							SimpleFormatter lf = new SimpleFormatter();  
							handler.setFormatter(lf);    
						    logger.info(message+recordLineNumber); 
			    	  }
			    	  recordLineNumber++;
		            }
		
			}
			   
			
		}
		catch(InvaidFormatException ex){
			ex.getMessage();
			
			
		}
		catch(FileNotFoundException ex){
			ex.getMessage();
			
			
		}catch(Exception ex){
			ex.getMessage();
			
			
		}
		finally{
			
		}
		return recordList;
	}
	
	
	
	/**
	 * Helper method for Identifying the current record's format from the file 
	 * 
	 * This method verifies the format of record from the DataTypes in the utilities package
	 * 
	 *@return String array
	 * 
	 */
public String[] getLineFormat(String[] records){
			
			
		String[] recordFormat = new String[records.length];
			
			DataTypeFactory factory ;
			int index=0;
			for(String record:records){
				record= record.replaceAll("^\"|\"$", "");
				factory= new DataTypeFactory(record);
				records[index] = record;
				recordFormat[index++]=factory.findFieldDataType().getType();
			}
				
		
		return recordFormat;
		
		
		
	}
	
	
/**
 * Helper method for checking whether a record is a valid or not based on the Valid format configuration in the XML 
 * 
 * This method verifies the format of record from the DataTypes in the utilities package
 * 
 *@return boolean
 * 
 */
	
	public boolean isLineFormatVaildate(String[] recordFormat){
		
		boolean isValidFormat =false;
			
			int index =0;
			for(LinkedHashMap<String, String> format: this.formats){
				
				if(Arrays.equals(format.values().toArray(), recordFormat)){
					this.formatIndex =index;
					isValidFormat =true;
				}
				index++;
			}
				
		
		return isValidFormat;
		
		
		
	}
	
	

}
