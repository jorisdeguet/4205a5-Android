package org.deguet;

import com.google.common.io.Files;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Path("/")
public class WebServicePics {

	@POST					@Path("/pics/post")
	@Produces(MediaType.APPLICATION_JSON)
	public String post(byte[] content) throws IOException {
		System.out.println("WS post pic ::: " + content.length);
		File dir = new File("image/");
		dir.mkdirs();
		File file = new File("image/" + new Date().getTime() + ".jpg");
		Files.write(content, file);
		return "bim";
	}

}
