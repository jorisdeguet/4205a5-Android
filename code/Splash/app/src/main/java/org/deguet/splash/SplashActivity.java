package org.deguet.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // call finish before startActivity or set nohistory in manifest

        Toast.makeText(this, "Start Splash", Toast.LENGTH_SHORT).show();
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
