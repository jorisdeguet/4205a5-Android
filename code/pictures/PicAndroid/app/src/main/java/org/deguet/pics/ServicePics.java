package org.deguet.pics;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by joris on 16-05-29.
 */
public interface ServicePics {

    String endPoint = "http://10.0.2.2:8888/";

    @POST("/rest/signin")
    Call<String> signin(@Body String s) ;

    @GET("/rest/signin/{s}/{s2}")
    Call<String> signin(@Path("s") String s, @Path("s2") String s2);

    @GET("/rest/signout")
    Call<String> signout();

    @GET("/rest/all")
    Call<String> all();


    @Multipart
    @POST("/rest/pics/post")
    Call<Boolean> postJPEG(@Part("file\"; filename=\"pp.png\" ") RequestBody file);

    @POST("/rest/pics/post")
    Call<Boolean> postBase64(@Body String b64);

    @POST("/rest/pics/post")
    Call<Boolean> postBytes(@Body RequestBody bytes);
}
