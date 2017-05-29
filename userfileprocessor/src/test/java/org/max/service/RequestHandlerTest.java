package org.max.service;

import static org.junit.Assert.assertEquals;

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
 * Handler class  Test
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */



public class RequestHandlerTest {
	/**
	 * 
	 *  This test is to check the status code of the http response
	 */
	
	public static final String BASE_URI = "http://localhost:8080/userfileprocessor/webapi/maxservice/";
	//@Test
	public void givenEndpointoftopURL_whenHTTPRequestIsMade_then200IsReceived()
	      throws ClientProtocolException, IOException{
	   // Given
	   HttpUriRequest request = new HttpGet(BASE_URI+"user/color");
	   
	   // When
	   HttpResponse httpResponse = new DefaultHttpClient().execute( request );
	   
	   // Then
	   assertEquals(Response.Status.OK.getStatusCode(), httpResponse.getStatusLine().getStatusCode());
	}
	
	
	
	/**
	 * 
	 *  This test is to check the response result
	 */
	//@Test
	public void givenEndpointoftopURL_whenHTTPRequestIsMade_thenTopreferrerURLsAreReceived()
	      throws ClientProtocolException, IOException{
	   // Given
	   HttpUriRequest request = new HttpGet(BASE_URI+"top");
	   
	   // When
	   HttpResponse httpResponse = new DefaultHttpClient().execute( request );
	   
	   // Then
	   
	   String json_string = EntityUtils.toString(httpResponse.getEntity());
	   JSONArray temp1=null;
	   try {
		   temp1 = new JSONArray(json_string);
		   	for(int i=0; i<temp1.length();i++){
			  // 	 ReferrerURL ref = new ObjectMapper().readValue( temp1.getJSONObject(i).toString(),  ReferrerURL.class);

			 	 //  assertEquals(Long.valueOf(17), ref.getHitCount());
			   	}
			   	
	} catch (JSONException e) {
		e.printStackTrace();
	}
	
	
	}

	/**
	 * 
	 *  This test is to check the status code of the http request for wrong url
	 */
	//@Test
	public void givenWrongEndpointoftopURL_whenHTTPRequestIsMade_thenTopreferrerURLsAreReceived()
	      throws ClientProtocolException, IOException{
		   // Given
		   HttpUriRequest request = new HttpGet(BASE_URI+"topped");
		   
		   // When
		   HttpResponse httpResponse = new DefaultHttpClient().execute( request );
		   
		   // Then
		   assertEquals(Response.Status.NOT_FOUND.getStatusCode(), httpResponse.getStatusLine().getStatusCode());
		}
	}
