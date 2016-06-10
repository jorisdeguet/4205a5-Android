package deguet.org.babytracker.service;

import org.deguet.model.MBaby;
import org.deguet.model.MBabyEvent;
import org.deguet.model.TBabyPlusLast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by joris on 16-04-30.
 */
public interface Service {


    @POST("/baby/add")
    Call<MBaby> addNewBaby(String name);

    @POST("/baby/event/add/{babyID}")
    Call<MBabyEvent> addBabyEvent(@Path("babyID") String babyID, @Body MBabyEvent.Type type);

    @GET("/home")
    List<TBabyPlusLast> listForHomeScreen();

    @GET("/home/{babyID}")
    List<MBabyEvent> last20EventsByTimeInverse(@Path("babyID") String babyID);

}
