package org.deguet.httpsandroid;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by joris on 16-04-30.
 * https://futurestud.io/blog/retrofit-2-log-requests-and-responses
 */
public class RetrofitHTTPS {

    ServiceHTTPS serviceHTTPS;

    public RetrofitHTTPS(){

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(ServiceHTTPS.endPoint)
                .client(getClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        serviceHTTPS = retrofit2.create(ServiceHTTPS.class);
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
