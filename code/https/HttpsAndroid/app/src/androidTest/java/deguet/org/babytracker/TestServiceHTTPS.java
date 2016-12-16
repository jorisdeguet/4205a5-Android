package deguet.org.babytracker;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.IOException;

/**
 * Created by joris on 16-05-29.
 */
public class TestServiceHTTPS extends AndroidTestCase {

    public void testHello() throws IOException {
        RetrofitHTTPS arh = new RetrofitHTTPS();
        Log.i("jorisdeguet","YAYAY");

        String test  = arh.serviceHTTPS.hello().execute().body();
        Log.i("jorisdeguet","------ ::" +test);
        assertTrue(test.length() > 10);
        Log.i("jorisdeguet","YUYUYUYUYUYU" );
    }

}
