package deguet.org.babytracker.service;

import java.util.List;

import deguet.org.babytracker.model.Baby;
import deguet.org.babytracker.model.BabyEvent;
import deguet.org.babytracker.model.User;
import deguet.org.babytracker.transfer.BabyPlusLast;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by joris on 16-04-30.
 */
public interface Service {


    @POST("/baby/add")
    Call<Baby> addNewBaby(String name);

    @POST("/baby/event/add/{babyID}")
    Call<BabyEvent> addBabyEvent(@Path("babyID") String babyID, @Body BabyEvent.Type type);

    @GET("/home")
    List<BabyPlusLast> listForHomeScreen();

    @GET("/home/{babyID}")
    List<BabyEvent> last20EventsByTimeInverse(@Path("babyID") String babyID);

}
