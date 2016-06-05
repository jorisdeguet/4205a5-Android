package deguet.org.babytracker;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import deguet.org.babytracker.model.User;
import deguet.org.babytracker.service.ServiceHTTP;
import deguet.org.babytracker.ui.BabyListFragment;
import deguet.org.babytracker.ui.events.BabySelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private User user;

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
        if (id == R.id.action_rx) {

            Observable<String> s = Observable.create(new Observable.OnSubscribe<String>() {

                @Override
                public void call(Subscriber<? super String> subscriber) {
                    ServiceHTTP s = new ServiceHTTP();
                    String res = s.test();
                    subscriber.onNext(res);
                    subscriber.onCompleted();
                }
            });
            s.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public final void onCompleted() {
                            SingletonBus.ottoBus.post("FINAL RX ");
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("RxRx", e.getMessage());
                        }

                        @Override
                        public final void onNext(String response) {
                            SingletonBus.ottoBus.post("RX " + response);
                        }
                    });

            return true;
        }
        if (id == R.id.action_retro) {
            BabyRetrofit arh = new BabyRetrofit();
            arh.service.geocode().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    SingletonBus.ottoBus.post("Retro " + response.message() + " "+response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    SingletonBus.ottoBus.post("Retro Error " + t.getMessage());
                }
            });
            return true;
        }
        if (id == R.id.action_rx_retro) {
            BabyRetrofit arh = new BabyRetrofit();
            arh.service.test("2629 saint Zotique").subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public final void onCompleted() {
                            SingletonBus.ottoBus.post("FINAL RX + Retro ");
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Toast.makeText(getApplicationContext(),"RX+Retro error :"+e,Toast.LENGTH_SHORT).show();
                            Log.e("RxRx", e.getMessage());
                        }

                        @Override
                        public final void onNext(String response) {
                            SingletonBus.ottoBus.post("RX + Retro " + response);
                        }
                    });
            return true;
        }

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
