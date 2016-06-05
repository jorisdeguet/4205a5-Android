package deguet.org.babytracker;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.IOException;

/**
 * Created by joris on 16-05-29.
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
