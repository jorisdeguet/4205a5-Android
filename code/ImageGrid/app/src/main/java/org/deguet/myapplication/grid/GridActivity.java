package org.deguet.myapplication.grid;

import android.graphics.Color;
import android.graphics.Picture;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import org.deguet.myapplication.Images;
import org.deguet.myapplication.R;
import org.deguet.myapplication.grid.GridAdapter;
import org.deguet.myapplication.stagger.ImagesAdapter;

import java.util.Random;

/**
 * Simple Grid View using a recycler
 */

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager(getApplicationContext(), 6);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        GridAdapter customAdapter = new GridAdapter(this, Images.imageThumbUrls);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView



    }
}
