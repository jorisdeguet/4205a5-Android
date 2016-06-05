package deguet.org.babytracker;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import deguet.org.babytracker.model.User;
import deguet.org.babytracker.service.ServiceBabyAuth;
import retrofit2.Response;

/**
 * Created by joris on 16-05-29.
 */
public class TestServiceBabyAuth extends AndroidTestCase {

    public void testLogin() throws IOException {
        BabyRetrofitSimple arh = new BabyRetrofitSimple();
        Log.i("jorisdeguet","YAYAY");
        ServiceBabyAuth.EmailPassword s = new ServiceBabyAuth.EmailPassword();
        s.email = "joris.deguet@gmail.com";
        s.password = "password";
        String test  = arh.serviceBabyAuth.signin(s).execute().body();
        Log.i("jorisdeguet","------ ::" +test);
        assertTrue(test.length() > 10);
        Log.i("jorisdeguet","YUYUYUYUYUYU" );

    }

    public void testSigninPost() throws IOException {
        BabyRetrofitSimple arh = new BabyRetrofitSimple();
        Log.i("jorisdeguet","YAYAY");
        ServiceBabyAuth.EmailPassword s = new ServiceBabyAuth.EmailPassword();
        s.email = "joris.deguet@gmail.com";
        s.password = "password";
        String test  = arh.serviceBabyAuth.signin(s).execute().body();
        Log.i("jorisdeguet","------ ::" +test);
        assertTrue(test.length() > 10);
        Log.i("jorisdeguet","YUYUYUYUYUYU" );

    }

    public void testSigninPostAndInvite() throws IOException {
        BabyRetrofitSimple arh = new BabyRetrofitSimple();
        Log.i("jorisdeguet","YAYAY");
        ServiceBabyAuth.EmailPassword s = new ServiceBabyAuth.EmailPassword();
        s.email = "joris.deguet@gmail.com";
        s.password = "password";
        User user  = arh.serviceBabyAuth.signup(s).execute().body();
        String test  = arh.serviceBabyAuth.signin(s).execute().body();
        //String signout = arh.serviceBabyAuth.signout().execute().body();
        Response<List<User>> response = arh.serviceBabyAuth.all().execute();
        if (response.isSuccessful()){
            List<User> users  = response.body();
            assertTrue(users.size() > 0);
            for (User u : users){
                Log.i("USERS" , u.toString());
            }
        }
        else{
            String message = response.errorBody().string();
            fail();
        }


    }

    public void testSignupSignin() throws IOException {
        BabyRetrofitSimple arh = new BabyRetrofitSimple();
        ServiceBabyAuth.EmailPassword s = new ServiceBabyAuth.EmailPassword();
        s.email = "joris.deguet@gmail.com";
        s.password = "password";
        User result  = arh.serviceBabyAuth.signup(s).execute().body();
        String token = arh.serviceBabyAuth.signin(s).execute().body();

    }
}
