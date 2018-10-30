package org.deguet.cookieandroid;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by joris on 16-04-30.
 * https://futurestud.io/blog/retrofit-2-log-requests-and-responses
 */
public class RetrofitUtil {

    Service service;

    public RetrofitUtil(){

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(Service.endPoint)
                .client(getClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        service = retrofit2.create(Service.class);

    }


    public static OkHttpClient getClient(){
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // builder.retryOnConnectionFailure(true);
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
