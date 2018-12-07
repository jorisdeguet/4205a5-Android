package org.deguet.myapplication.stagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import org.deguet.myapplication.Images;
import org.deguet.myapplication.R;
import org.deguet.myapplication.stagger.ImagesAdapter;

public class StaggeredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(6, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        ImagesAdapter customAdapter = new ImagesAdapter(this, Images.imageThumbUrls);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
}
