package deguet.org.babytracker.service;

import java.util.List;

import deguet.org.babytracker.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by joris on 16-05-29.
 */
public interface ServiceBabyAuth {

    public static class EmailPassword {public String email,password;}

    String endPoint = "https://10.0.2.2:9999/";

    //@GET("/rest/signin/{email}/{password}")
    //Call<String> signin(@Path("email") String email, @Path("password") String password);

    @POST("/rest/social/signin")
    Call<String> signin(@Body EmailPassword s) ;

    @GET("/rest/social/signout")
    Call<String> signout();

    @POST("/rest/social/signup")
    Call<User> signup(@Body EmailPassword signup);

    @POST("/rest/social/invite")
    Call<String> sendInvites(@Body String s) ;

    @GET("/rest/social/all")
    Call<List<User>> all();
}
