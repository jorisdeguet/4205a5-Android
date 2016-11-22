package deguet.org.babytracker;

import android.test.AndroidTestCase;
import android.util.Log;


import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by joris on 16-05-29.
 */
public class TestServiceCookie extends AndroidTestCase {

    public void testSigninPostAndGetAll() throws IOException {
        RetrofitCookie arh = new RetrofitCookie();
        Log.i("jorisdeguet","YAYAY");
        String cookie  = arh.serviceCookie.signin("coucou c moi").execute().body();
        //String signout  = arh.serviceCookie.signout().execute().body();
        Response<String> response = arh.serviceCookie.all().execute();
        if (response.isSuccessful()){
            String strings  = response.body();
            Log.i("STRINGS-RESULT",strings);
        }
        else{
            String message = response.errorBody().string();
            fail();
        }
    }

    public void testSigninPostAndSignoutAndGetAll() throws IOException {
        RetrofitCookie arh = new RetrofitCookie();
        Log.i("jorisdeguet","YAYAY");
        String cookie  = arh.serviceCookie.signin("coucou c moi").execute().body();
        String signout  = arh.serviceCookie.signout().execute().body();
        Response<String> response = arh.serviceCookie.all().execute();
        if (response.isSuccessful()){
            String strings  = response.body();
            Log.i("STRINGS-RESULT",strings);
            fail();
        }
        else{
            String message = response.errorBody().string();
        }
    }


}
