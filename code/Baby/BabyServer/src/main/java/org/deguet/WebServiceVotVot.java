package org.deguet;

import com.google.gson.Gson;
import org.deguet.Exc.BadCredentials;
import org.deguet.Exc.BadEmail;
import org.deguet.Exc.NoToken;
import org.deguet.model.NQToken;
import org.deguet.model.NQPerson;
import org.deguet.model.C2SLoginPassword;
import org.deguet.model.C2SSignUpRequest;
import org.deguet.model.S2CPersonWithoutPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class WebServiceVotVot {

	final Logger logger = LoggerFactory.getLogger(getClass());

	public final static String Cookie = "votvot-id";

	private Gson gson;

	public WebServiceVotVot() throws BadEmail{
		gson = CustomGson.getIt();
	}

	@POST					@Path("/social/signin")
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response signin(C2SLoginPassword creds)
            throws BadCredentials{
        C2SLoginPassword lp = creds;//gson.fromJson(json, C2SLoginPassword.class);
        logger.debug("WS SOCIAL : SIGNIN request " + lp.toString());
        NQToken token = Services.vote.signin(lp.email,lp.password);

        NewCookie cookiee = new NewCookie(Cookie, token.getId(), "/", "", "id token", 604800, false);
        logger.debug("WS SOCIAL : SIGNIN Success Cookie " + cookiee.toString() + " " +cookiee.getPath());
        return Response.ok(gson.toJson(token.getId()),MediaType.APPLICATION_JSON)
                .cookie(cookiee)
                .build();
	}



	@GET					@Path("/signin/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String signinGet(@PathParam("email") String login, @PathParam("password") String password) throws BadCredentials{
		logger.debug("WS SOCIAL : SIGN IN THROUGH GET");
		C2SLoginPassword lp = new C2SLoginPassword();
		lp.email = login;
		lp.password = password;
		return gson.toJson("Hello "+ login);
	}


	// http://localhost:8080/rest/social/returnin/80bbdd51-a02c-4281-8630-d2012113473a
	@GET					@Path("/social/returnin/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returninGet(@PathParam("token") String token) throws NoToken{
        logger.debug("WS SOCIAL : Return In " + token );
        NQPerson p = Services.vote.returnWithToken(token);
        return Response.ok(gson.toJson(new S2CPersonWithoutPassword(p)),MediaType.APPLICATION_JSON).build();

	}

	@POST					@Path("/social/signup")
	@Produces(MediaType.APPLICATION_JSON)
	public NQPerson signup(C2SSignUpRequest person) throws BadEmail, NoSuchAlgorithmException, Exc.MaxReached, Exc.BadSex, UnsupportedEncodingException, Exc.BadBirth {
		// Get the person that is candidate
        logger.debug("WS SOCIAL : SIGNUP REQUEST " + person);
		C2SSignUpRequest p = person;//gson.fromJson(person, C2SSignUpRequest.class);
        logger.debug("WS SOCIAL : SIGNUP REQUEST person :::: " + p);
        NQPerson persisted = Services.vote.signUp(p);
        return persisted;

	}

	@GET					@Path("/social/signout")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response signout(@CookieParam(Cookie) Cookie cookie) throws BadEmail{
		// todo the service signout by erasing the token
		logger.debug("WS SOCIAL : SIGNOUT REQUEST " + cookie);
		// erase the cookie
		if (cookie == null) return Response.ok("No cookie",MediaType.TEXT_PLAIN).build();
        logger.debug("WS SOCIAL : SIGNOUT REQUEST delete tokens" + cookie.getValue());
        Services.vote.signout(cookie.getValue());
        logger.debug("WS SOCIAL : SIGNOUT REQUEST forge new cookie to die " );
		NewCookie toDelete = new NewCookie(Cookie, null, "/", null, null, 0, false);
        Response res = Response.ok(gson.toJson(true),MediaType.TEXT_PLAIN)
				.cookie(toDelete)
				.build();
        logger.debug("WS SOCIAL : SIGNOUT REQUEST forgeD "+toDelete );
		return res;
	}

    @GET					@Path("/social/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NQPerson> all(@CookieParam(Cookie) Cookie cookie) throws NoToken {
        logger.debug("WS SOCIAL : ALL REQUEST " + cookie);
        if (cookie == null) throw new NoToken();
        return Services.vote.allPeople();
    }

}
