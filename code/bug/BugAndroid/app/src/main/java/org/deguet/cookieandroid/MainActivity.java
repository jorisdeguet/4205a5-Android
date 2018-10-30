package org.deguet.cookieandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RetrofitUtil r = new RetrofitUtil();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.service.error400("").enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            //Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                            Log.i("GNAGNA", "OK");
                        } else {
                            //Toast.makeText(MainActivity.this, "Ko", Toast.LENGTH_SHORT).show();
                            try {
                                Log.i("GNAGNA", "KO " +response.errorBody().string());
                            }catch (Throwable t) {}
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Dead", Toast.LENGTH_SHORT).show();
                        Log.i("GNAGNA", t.toString());
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}
