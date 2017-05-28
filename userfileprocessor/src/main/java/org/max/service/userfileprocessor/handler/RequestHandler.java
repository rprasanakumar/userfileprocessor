package org.max.service.userfileprocessor.handler;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.service.CommonServiceUserDataImpl;
import org.max.service.userfileprocessor.service.ICommonService;
import org.max.service.userfileprocessor.service.IUserDataDisplay;
import org.max.service.userfileprocessor.service.UserDataDisplayColor;
import org.max.service.userfileprocessor.service.UserDataDisplayColorName;

import com.mysql.fabric.Response;



/** 
 * Handler class  redirecting request to service 
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */


@Path("/maxservice")
public class RequestHandler {
	
	ICommonService service;
	/**
	 * 
	 * @post request handler method with
	 * @param ReferrerURL object
	 *
	 */
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/user/color")
		
public List<UserRecordDisplay> getUserColorCount() {
		IUserDataDisplay displayType = new UserDataDisplayColor();
		service = new CommonServiceUserDataImpl(displayType);
		 List<UserRecordDisplay> record = (List<UserRecordDisplay>) service.execute();
		 
		 return record;
}



@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/user/color/name")
	
	
public List<UserRecordDisplay> getUserColorCountUserName() {
	IUserDataDisplay displayType = new UserDataDisplayColorName();
	service = new CommonServiceUserDataImpl(displayType);
	List<UserRecordDisplay> record = (List<UserRecordDisplay>) service.execute();
	return record;
}
	
	
}
