package org.deguet;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by joris on 15-10-15.
 */
@Provider
public class CatchAllMapper implements ExceptionMapper<Exception> {

    @Override
    public javax.ws.rs.core.Response toResponse(Exception ex) {
        ex.printStackTrace();
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity(ex.getClass().getSimpleName()).build();
    }

}