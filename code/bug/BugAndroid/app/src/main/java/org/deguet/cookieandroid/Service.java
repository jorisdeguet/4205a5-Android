package org.deguet.cookieandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by joris on 16-05-29.
 */
public interface Service {

    //String endPoint = "http://10.0.2.2:7050/";
    String endPoint = "http://5a5.di.college-em.info:7050/";

    @POST("/rest/signin")
    Call<String> error400(@Body String s);

    @POST("/rest/ok")
    Call<String> ok200(@Body String s);
}
