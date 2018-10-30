package org.deguet;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Randomly happens on some requests
 * No matter if it runs on localhost on deployment server
 *
 * Works Ok on real android device
 * Randomly fails on AVD
 *
 * org.deguet.cookieandroid W/System.err: java.net.ProtocolException: unexpected end of stream
 *         at okhttp3.internal.http1.Http1Codec$FixedLengthSource.read(Http1Codec.java:409)
 *         at okio.RealBufferedSource.readAll(RealBufferedSource.java:173)
 *         at retrofit2.Utils.buffer(Utils.java:298)
 *         at retrofit2.OkHttpCall.parseResponse(OkHttpCall.java:203)
 *
 * read https://github.com/square/okhttp/issues/2738
 *  - tried .retryOnConnectionFailure(true) no change
 *
 * read https://github.com/square/retrofit/issues/1916
 *
 *
 * read https://github.com/square/okhttp/issues/3589  POTENTIAL
 *
 * PISTE l'émulateur android breaks content-length
 *
 * Limiter à emulateur sur Windows 10, test si la version de SDK est pas 7.1.1
 *
 *
 */

@Path("/")
public class WebServiceCookie {

	@POST					@Path("/signin")
	public Response signin(String s) {
		Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
		Response res =  rBuild.type(MediaType.TEXT_PLAIN)
				.encoding("UTF-8")
				.entity("IllegalArgumentException")
				.build();

		return res;
	}

	@POST					@Path("/ok")
	public Response ok(String s) {
        return Response.ok("yeah",MediaType.TEXT_PLAIN)
                .build();
	}
}
