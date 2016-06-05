package org.deguet;

import org.deguet.Exc.NoSuchPoll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Random;

/**
 * This web service is only used for development and tests purpose.
 * 
 * Methods only work when call from localhost
 * @author joris
 *
 */

@Path("/")
public class WebServiceDev {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean isLocalHost(UriInfo uriInfo){
		return uriInfo.getBaseUri().getHost().contains("localhost");
	} 

	
	@GET 
	@Path("flushfortests")
	public String flushForTests(@Context UriInfo uriInfo){
		// do it only for request from localhost
		boolean isLocalHost = isLocalHost(uriInfo);
		logger.debug("WS DEV : Resquest to erase everything. On localhost ?" + isLocalHost +" "+uriInfo.getBaseUri().getHost());
		if (isLocalHost){
			// do the flushing
			logger.debug("WS DEV : Deleting people");
			Services.vote.deletePeople();
			return "ok";
		}
		return "only available on localhost for tests";
	}


	@GET 
	@Path("initial")
	public String initial(@Context UriInfo uriInfo){
		// do it only for request from localhost
		boolean isLocalHost = isLocalHost(uriInfo);
		logger.debug("WS DEV : DEV load. On localhost ?" + isLocalHost +" "+uriInfo.getBaseUri().getHost());
		if (isLocalHost){
			// do the flushing
			Services.initial.createSampleAll(1);
			return "ok";
		}
		return "only available on localhost for tests";
	}



	@GET 
	@Path("initial/people/{sizeBy100}")
	public String initialPeople(@Context UriInfo uriInfo, @PathParam("sizeBy100")  Integer size){
		// do it only for request from localhost
		boolean isLocalHost = isLocalHost(uriInfo);
		logger.debug("WS DEV : DEV load people. On localhost ?" + isLocalHost +" "+uriInfo.getBaseUri().getHost());
		if (isLocalHost){
			// do the flushing
			try {
				Services.initial.createSamplesUsers(new Random(123),size);
				return "ok";
			} catch (Exception e) {
				e.printStackTrace();
				return e.getClass().getSimpleName();
			}
		}
		return "only available on localhost for tests";
	}
	

}
