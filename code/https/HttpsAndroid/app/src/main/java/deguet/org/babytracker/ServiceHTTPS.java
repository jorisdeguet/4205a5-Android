package deguet.org.babytracker;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by joris the boss.
 */
public interface ServiceHTTPS {

    String endPoint = "https://10.0.2.2:9999/";

    @GET("/rest/hello")
    Call<String> hello() ;


}
