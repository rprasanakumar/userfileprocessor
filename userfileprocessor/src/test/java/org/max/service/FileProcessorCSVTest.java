package org.max.service;

import  org.junit.Assert.*;
import org.junit.Before;



import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.service.FileProcessorCSV;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

/**
 * @author Prasanna Kumar Rajendran
 * Test for FileProcessCSV service Class
 */
public class FileProcessorCSVTest{
	
	public static String FILE_PATH = "C:\\tmp\\";
	String fileName;
	
	
	   @Before
	    public void beforeClass() throws Exception {
		   
		   fileName = "file.txt";
		
	    }
	
	
	/**
	 * 
	 * Method checks the Number of valid records being processed 
	 */
	@Test
	public void givenFileWithRecords_whenRecordsParsed_thenNumberOfValidRecordsAreReceived()
	      throws ClientProtocolException, IOException{
	   // Given
		
		FileProcessorCSV file = new FileProcessorCSV(FILE_PATH+fileName);
	   
	   // When
		List<UserRecord> record = file.parse();
	   
	   // Then
	   assertEquals(7, record.size());
	}
	

	   
}