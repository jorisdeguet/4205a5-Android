package deguet.org.babytracker;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import deguet.org.babytracker.service.Service;
import deguet.org.babytracker.service.ServiceGeocode;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by joris on 16-04-30.
 * https://futurestud.io/blog/retrofit-2-log-requests-and-responses
 */
public class BabyRetrofit {

    Retrofit retrofit;

    ServiceGeocode service;

    public BabyRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceGeocode.endPoint)
                //.addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(ServiceGeocode.class);
    }

}
