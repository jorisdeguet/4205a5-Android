package deguet.org.babytracker;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by joris on 16-04-30.
 * https://futurestud.io/blog/retrofit-2-log-requests-and-responses
 */
public class RetrofitCookie {

    String token = "coucou";

    Retrofit retrofit;

    ServiceCookie serviceCookie;

    ServiceCookie mockService;

    public RetrofitCookie(){

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(ServiceCookie.endPoint)
                .client(getClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        serviceCookie = retrofit2.create(ServiceCookie.class);

    }

    public static class MyCookieJar implements CookieJar {

        private List<Cookie> cookies;

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            this.cookies =  cookies;
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> res = new ArrayList<>();
            if (cookies != null){
                for(Cookie c : cookies){
                    if (c.expiresAt() > System.currentTimeMillis()) res.add(c);
                }
            }
            return res;
        }
    }

    public static OkHttpClient getClient(){
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // Sets the cookie Jar to automatically handles incoming and outgoing cookies
            CookieJar cookieJar =
                    new MyCookieJar();
            builder = builder.cookieJar(cookieJar);

            // Adds logging capability to see http exchanges on Android Monitor
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder = builder.addInterceptor(interceptor);
            return builder.build();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
