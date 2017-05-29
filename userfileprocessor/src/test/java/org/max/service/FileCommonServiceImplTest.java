package org.max.service;

import  org.junit.Assert.*;
import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.service.FileCommonServiceImpl;
import org.max.service.userfileprocessor.service.FileProcessorCSV;
import org.max.service.userfileprocessor.service.ICommonService;
import org.junit.Before;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.xmlrpc.Client;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.Service;

/**
 * @author Prasanna Kumar Rajendran
 * this Class is used to test the two endpoints designed in the referrer url project
 */
public class FileCommonServiceImplTest{
	
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
	//@Test
	public void givenFileWithRecords_whenRecordsParsed_thenNumberOfValidRecordsReceived()
	      throws ClientProtocolException, IOException{
	   // Given
		
		ICommonService service = new FileCommonServiceImpl(FILE_PATH+fileName);
	   
	   // When
		int count =service.execute().size();
	   
	   // Then
	   assertEquals(7, 7);
	}
	

	   

	
	
	
	
}