package org.deguet.pics;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
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

    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("org.deguet.cookieandroid", appContext.getPackageName());
    }

    @Test
    public void testSigninPostAndGetAll() throws IOException {
        HttpUtil arh = new HttpUtil();
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
        HttpUtil arh = new HttpUtil();
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
