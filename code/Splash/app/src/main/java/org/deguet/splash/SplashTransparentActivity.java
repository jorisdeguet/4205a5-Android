package org.deguet.splash;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class SplashTransparentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_transparent);

        // Ceci est un timeout, à rmeplacer par un réel appel au serveur pour vérifier si je suis déjà connecté
        Handler handler = new Handler();
        final Random r = new Random();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (r.nextBoolean()){
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        }, 5000);
    }
}
