package org.deguet;

import org.deguet.Exc.NoToken;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by joris on 15-10-13.
 */
@Provider
public class VotvotExceptionMapper implements ExceptionMapper<NoToken> {

    @Override
    public javax.ws.rs.core.Response toResponse(NoToken ex) {
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity("NoToken").build();
    }

}
