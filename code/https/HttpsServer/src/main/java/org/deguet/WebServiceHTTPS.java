package org.deguet;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class WebServiceHTTPS {

	@GET					@Path("/hello")
	public String hello() {
		System.out.println("Access to Hello web service " );
        return "Is it me you're looking for";
	}



}
