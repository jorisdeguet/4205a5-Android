package deguet.org.babytracker;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.IOException;

/**
 * Created by joris on 16-05-29.
 *
 * http://riggaroo.co.za/retrofit-2-mocking-http-responses/ tests without the service implemented
 * http://stackoverflow.com/questions/34740665/how-to-mock-services-with-retrofit-2-0-and-the-new-mockretrofit-class
 *
 */
public class TestRetrofit extends AndroidTestCase{


    public void testSimple() throws IOException {
        Log.i("jorisdeguet","YOYOYOYO");
        BabyRetrofitSimple arh = new BabyRetrofitSimple();
        Log.i("jorisdeguet","YAYAY");
        String test  = arh.service.geocode().execute().body();
        Log.i("jorisdeguet","------ ::" +test.substring(0,100));
        assertTrue(test.length() > 10);
        Log.i("jorisdeguet","YUYUYUYUYUYU" );

    }

}
