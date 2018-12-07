package org.deguet.myapplication.quilt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.deguet.myapplication.Images;
import org.deguet.myapplication.R;
import org.deguet.myapplication.quilt.QuiltView;

import java.util.ArrayList;
import java.util.List;

/**
 * tout pris sur https://stackoverflow.com/questions/17716325/show-images-from-gallery-in-mosaic-style-in-android
 */

public class QuiltActivity extends AppCompatActivity {

    QuiltView quiltView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilt);
        this.quiltView = (QuiltView) findViewById(R.id.quilt);
        addTestQuilts(200);
    }

    public void addTestQuilts(int num){
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        List<ImageView> images = new ArrayList<>();
        for (String url : Images.imageThumbUrls) {
            ImageView iv = new ImageView(this.getApplicationContext());
            Glide.with(this).load(url).apply(options).into(iv);
            images.add(iv);
        }
        quiltView.addPatchImages(images);
    }
}
