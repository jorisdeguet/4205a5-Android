package deguet.org.babytracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TrackBabyActivity extends AppCompatActivity {

    @Override public void onResume() { SingletonBus.guavaBus.register(this);     super.onResume();}
    @Override public void onPause() {   SingletonBus.guavaBus.unregister(this);    super.onPause();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String uuid = getIntent().getStringExtra("uuid");
        Toast.makeText(getApplicationContext(), "BABY " + uuid, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_track_baby);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_track_baby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
