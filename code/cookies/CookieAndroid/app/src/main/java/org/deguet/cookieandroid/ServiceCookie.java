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
public interface ServiceCookie {

    String endPoint = "http://10.0.2.2:8888/";

    @POST("/rest/signin")
    Call<String> signin(@Body String s) ;

    @GET("/rest/signin/{s}/{s2}")
    Call<String> signin(@Path("s") String s, @Path("s2") String s2);

    @GET("/rest/signout")
    Call<String> signout();

    @GET("/rest/all")
    Call<String> all();
}
