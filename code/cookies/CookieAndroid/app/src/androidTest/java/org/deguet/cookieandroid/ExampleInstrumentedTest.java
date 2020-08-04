package org.deguet.cookieandroid;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
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

    @Test
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
