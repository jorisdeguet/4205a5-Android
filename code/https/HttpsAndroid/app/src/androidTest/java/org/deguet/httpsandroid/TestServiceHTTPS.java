package org.deguet.httpsandroid;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by joris on 16-05-29.
 */
@RunWith(AndroidJUnit4.class)
public class TestServiceHTTPS  {

    @Test
    public void testHello() throws IOException {
        RetrofitHTTPS arh = new RetrofitHTTPS();
        Log.i("jorisdeguet","YAYAY");

        String test  = arh.serviceHTTPS.hello().execute().body();
        Log.i("jorisdeguet","------ ::" +test);
        Assert.assertTrue(test.length() > 10);
        Log.i("jorisdeguet","YUYUYUYUYUYU" );
    }

}
