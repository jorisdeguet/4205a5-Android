package org.deguet.cookieandroid;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Response;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void testError() {
        int normal = 0;
        int errors = 0;
        int buggy = 0;
        RetrofitUtil arh = new RetrofitUtil();
        for (int i = 0 ; i < 300 ; i++){
            try{
                Response<String> resp = arh.service.error400(" ").execute();
                if (resp.isSuccessful()) {
                    normal++;
                    Log.i("BUGGYBUG", "Ok ");
                } else {
                    errors++;
                    Log.i("BUGGYBUG", "Ko ");
                }
            }catch (Exception e){
                buggy++;
                Log.i("BUGGYBUG", e.getClass().getCanonicalName());
            }
        }
        Log.i("BUGGYBUG", normal + " " + errors + " " + buggy);
        System.out.println(normal + " " + errors + " " + buggy);
    }

    @Test
    public void testOk() throws IOException {
        int normal = 0;
        int errors = 0;
        int buggy = 0;
        RetrofitUtil arh = new RetrofitUtil();
        for (int i = 0 ; i < 300 ; i++){
            try{
                Response<String> resp = arh.service.ok200(" ").execute();
                if (resp.isSuccessful()) {
                    normal++;
                    Log.i("BUGGYBUG", "Ok ");
                } else {
                    errors++;
                    Log.i("BUGGYBUG", "Ko ");
                }
            }catch (Exception e){
                buggy++;
                Log.i("BUGGYBUG", e.getClass().getCanonicalName());
            }
        }
        Log.i("BUGGYBUG", normal + " " + errors + " " + buggy);
        System.out.println(normal + " " + errors + " " + buggy);
    }

}
