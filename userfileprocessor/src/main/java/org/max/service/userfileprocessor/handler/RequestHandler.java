package org.max.service.userfileprocessor.handler;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.bean.UserVenue;
import org.max.service.userfileprocessor.service.CommonServiceUserAPIImpl;
import org.max.service.userfileprocessor.service.CommonServiceUserDataImpl;
import org.max.service.userfileprocessor.service.FileCommonServiceImpl;
import org.max.service.userfileprocessor.service.ICommonService;
import org.max.service.userfileprocessor.service.IUserDataDisplay;
import org.max.service.userfileprocessor.service.RecordCommonServiceImpl;
import org.max.service.userfileprocessor.service.UserDataDisplayAPI;
import org.max.service.userfileprocessor.service.UserDataDisplayColor;
import org.max.service.userfileprocessor.service.UserDataDisplayColorName;
import com.mysql.fabric.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;




/** 
 * Handler class  redirecting request to service 
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */


@Path("user/")
public class RequestHandler {
	
	ICommonService service;
	
	
	/**
	 * request handler method for getting 
	 * the Color and relative count
	 *@return list of type  UserRecordDisplay
	 *
	 */
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("color")
		
public List<UserRecordDisplay> getUserColorCount() {
		String query="";
		IUserDataDisplay displayType = new UserDataDisplayColor();
		service = new CommonServiceUserDataImpl(displayType,query);
		 List<UserRecordDisplay> record = (List<UserRecordDisplay>) service.execute();
		 
		 return record;
}




/**
 * request handler method for getting 
 * the Color, relative count and list of User Names
 *@return list of type  UserRecordDisplay
 *
 */

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("name")
	
	
public List<UserRecordDisplay> getUserColorCountUserName() {
	String query ="";
	IUserDataDisplay displayType = new UserDataDisplayColorName();
	service = new CommonServiceUserDataImpl(displayType,query);
	List<UserRecordDisplay> record = (List<UserRecordDisplay>) service.execute();
	return record;
}





/**
 * request handler method for getting 
 * the venues from the Four SquareAPI
 *@return list of type  UserVenue
 *
 */

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("venue/{venueQuery}/{location}")
	
	
public List<UserVenue> getUserVenue(@PathParam("venueQuery") String venueQuery,@PathParam("location") String location) {
	String query =venueQuery;
	UserRecord user = new UserRecord();
	user.setLat(location);
	IUserDataDisplay displayType = new UserDataDisplayAPI(user);
	service = new CommonServiceUserAPIImpl(displayType,query);
	List<UserVenue> record = (List<UserVenue>) service.execute();
	
	return record;
}


/**
 * 
 * @post request handler method for posting the file
 * @param ReferrerURL object
 *
 */
@POST
@Path("file")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public  List<UserRecordDisplay> postUserFile(
        @FormDataParam("file") InputStream uploadedInputStream,
        @FormDataParam("file") FormDataContentDisposition fileDetail){
	String path ="/tmp/";
	List<UserRecordDisplay> resultList;
	String fileLocation = path
	            + fileDetail.getFileName();
	FileCommonServiceImpl service = new FileCommonServiceImpl(fileLocation);
	 service.writeFile(uploadedInputStream, fileLocation);
	 resultList= (List<UserRecordDisplay>) service.execute();
	 
	return resultList;
}


/**
 * 
 * @post request handler method for posting one record
 * @param ReferrerURL object
 *
 */
@POST
@Path("sendrecord")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public  List<UserRecord> postUserRecord(UserRecord record){

	service = new RecordCommonServiceImpl(record);
	List<UserRecord> resultList = (List<UserRecord>) service.execute();
	return resultList;
}





	
}
