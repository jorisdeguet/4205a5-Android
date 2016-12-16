package org.deguet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class WebServiceVotVot {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@GET					@Path("/hello")
	public String signinGet(@PathParam("email") String login, @PathParam("password") String password) {
		logger.debug("Access to Hello web service");
        return "Is it me you're looking for";
	}



}
