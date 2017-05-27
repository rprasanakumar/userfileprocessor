package org.max.service.userfileprocessor.handler;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.max.service.userfileprocessor.service.CommonServiceUserDataImpl;
import org.max.service.userfileprocessor.service.ICommonService;

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
/*	@POST
	@Path("/url")
	public ReferrerURL postUrl(ReferrerURL referrer){
		
		try {
			service = new ReferrerServiceImplementation(referrer);
			return service.putDataURL();
		} catch (URISyntaxException e) {
			e.printStackTrace();	
		}
		return referrer;
	}*/
	
	
	
	public void getUserColorNCount() {
		
		service = new CommonServiceUserDataImpl();
		service.execute();
		
		
		
	}
	
	
}
