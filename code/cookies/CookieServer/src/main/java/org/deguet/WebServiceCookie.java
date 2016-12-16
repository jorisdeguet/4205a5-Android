package org.deguet;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/")
public class WebServiceCookie {

    public static class NoCookie extends Exception{}

	final Logger logger = LoggerFactory.getLogger(getClass());

	public final static String Cookie = "votvot-id";

	private Gson gson;

	public WebServiceCookie() {
		gson = new Gson();
	}

	@POST					@Path("/signin")
	public Response signin(String s) {
        logger.debug("WS SOCIAL : SIGNIN request " + s);
        String fakeToken = UUID.randomUUID().toString();
        NewCookie cookiee = new NewCookie(Cookie, fakeToken, "/", "", "id token", 604800, false);
        logger.debug("WS SOCIAL : SIGNIN Success Cookie " + cookiee.toString() + " " +cookiee.getPath());
        return Response.ok(gson.toJson(fakeToken),MediaType.APPLICATION_JSON)
                .cookie(cookiee)
                .build();
	}

	@GET					@Path("/signin/{s}/{s2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signinGet(@PathParam("s") String s, @PathParam("s") String s2) {
		return signin(s);
	}

	@GET					@Path("/signout")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response signout(@CookieParam(Cookie) Cookie cookie) {
		// todo the service signout by erasing the token
		logger.debug("WS SOCIAL : SIGNOUT REQUEST " + cookie);
		// erase the cookie
		if (cookie == null) return Response.ok("No cookie",MediaType.TEXT_PLAIN).build();
        logger.debug("WS SOCIAL : SIGNOUT REQUEST forge new cookie to die " );
		NewCookie toDelete = new NewCookie(Cookie, null, "/", null, null, 0, false);
        Response res = Response.ok(gson.toJson(true),MediaType.TEXT_PLAIN)
				.cookie(toDelete)
				.build();
        logger.debug("WS SOCIAL : SIGNOUT REQUEST forgeD "+toDelete );
		return res;
	}

    @GET					@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String all(@CookieParam(Cookie) Cookie cookie) throws NoCookie {
        logger.debug("WS SOCIAL : ALL REQUEST  with cookie ::: " + cookie);
        if (cookie == null) throw new NoCookie();
        // build the list
        List<String> res = Arrays.asList("Jo","Mo","To","Yo");
        return res.subList(0,new Random().nextInt(res.size())).toString();
    }

}
