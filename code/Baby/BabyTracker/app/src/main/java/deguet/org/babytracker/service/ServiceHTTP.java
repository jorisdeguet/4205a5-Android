package deguet.org.babytracker.service;

import java.io.IOException;

import deguet.org.babytracker.SingletonBus;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by joris on 16-05-03.
 */
public class ServiceHTTP {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String test(){
        try {
            String res = run("http://www.college-em.info");
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OOPS";
    }
}
