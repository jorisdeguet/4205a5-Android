package org.deguet.progress;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    ProgressBar progress;
    TextView stuff;

    ProgressDialog progressD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        progress = (ProgressBar) findViewById(R.id.progress);
        stuff = (TextView) findViewById(R.id.stuff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
                new TTask<>().execute();
            }
        });

        // show progress
        progressD = ProgressDialog.show(MainActivity.this, "Please wait",
                "Long operation starts...", true);
        // start the task that will stop it
        new DialogTask<>().execute();
    }

    private void startDownload(){
        progress.setVisibility(View.VISIBLE);
        stuff.setVisibility(View.GONE);
    }

    private void endDownload(){
        progress.setVisibility(View.GONE);//INVISIBLE occupe de l'espace GONE non
        stuff.setVisibility(View.VISIBLE);
    }

    class TTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            endDownload();
            super.onPostExecute(c);
        }

        @Override
        protected C doInBackground(A... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    class DialogTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            progressD.dismiss();
            super.onPostExecute(c);
        }

        @Override
        protected C doInBackground(A... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
