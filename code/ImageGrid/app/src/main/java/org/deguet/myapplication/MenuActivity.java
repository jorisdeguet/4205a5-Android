package org.deguet.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.deguet.myapplication.grid.GridActivity;
import org.deguet.myapplication.packed.QuiltPackedActivity;
import org.deguet.myapplication.quilt.QuiltActivity;
import org.deguet.myapplication.stagger.StaggeredActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.quilt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), QuiltActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.stagger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StaggeredActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GridActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.packed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), QuiltPackedActivity.class);
                startActivity(i);
            }
        });
    }
}
