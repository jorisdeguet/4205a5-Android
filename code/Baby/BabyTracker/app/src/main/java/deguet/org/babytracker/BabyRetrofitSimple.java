package deguet.org.babytracker;

import android.util.Log;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import deguet.org.babytracker.service.ServiceBabyAuth;
import deguet.org.babytracker.service.ServiceGeocode;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by joris on 16-04-30.
 * https://futurestud.io/blog/retrofit-2-log-requests-and-responses
 */
public class BabyRetrofitSimple {

    String token = "coucou";

    Retrofit retrofit;

    ServiceGeocode service;

    ServiceBabyAuth serviceBabyAuth;

    public BabyRetrofitSimple(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceGeocode.endPoint)
                //.addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        service = retrofit.create(ServiceGeocode.class);





        //builder..(cookieManager);
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(ServiceBabyAuth.endPoint)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))   // TODO use custom Gson
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        serviceBabyAuth = retrofit2.create(ServiceBabyAuth.class);
    }

    public static class MyCookieJar implements CookieJar {

        private List<Cookie> cookies;

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            Log.i("COOOKIES",cookies.toString());
            this.cookies =  cookies;
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> res = Lists.newArrayList();
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
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            // configure the builder to accept all SSL certificates
            builder = builder.sslSocketFactory(sslSocketFactory);
            // configure the builder to accept all hostnames includint localhost
            builder = builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
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
