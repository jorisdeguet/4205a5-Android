package org.deguet.progressbar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    ProgressBar progress;
    TextView stuff;

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
    }

    private void startDownload(){
        progress.setVisibility(View.VISIBLE);
        stuff.setVisibility(View.INVISIBLE);
    }

    private void endDownload(){
        progress.setVisibility(View.INVISIBLE);
        stuff.setVisibility(View.VISIBLE);
    }

    class TTask<A,B,C> extends AsyncTask<A,B,C>{

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
}
