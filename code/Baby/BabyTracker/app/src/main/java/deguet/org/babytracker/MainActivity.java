package deguet.org.babytracker;

import android.content.Intent;
import android.database.Observable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.deguet.model.MUser;

import deguet.org.babytracker.service.ServiceHTTP;
import deguet.org.babytracker.ui.BabyListFragment;
import deguet.org.babytracker.ui.events.BabySelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MUser user;

    @Override public void onResume() {
        //SingletonBus.guavaBus.register(this);
        SingletonBus.ottoBus.register(this);
        super.onResume();
    }
    @Override public void onPause() {
        //SingletonBus.guavaBus.unregister(this);
        SingletonBus.ottoBus.unregister(this);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton b = (FloatingActionButton)findViewById(R.id.fab);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Click on +",Toast.LENGTH_SHORT).show();
            }
        });

        SingletonBus.ottoBus.post("COucou");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //////////////// handlers for event

    @Subscribe
    public void babySelected(BabySelected sel){
        Intent intent = new Intent(getApplicationContext(), TrackBabyActivity.class);
        intent.putExtra("uuid",sel.uuid+"");
        startActivity(intent);
    }

    @Subscribe
    public void googleRes(String sel){
        Toast.makeText(getApplicationContext(),"REs :"+sel,Toast.LENGTH_SHORT).show();
        Log.i("Otto","::::" + sel);
    }
}
