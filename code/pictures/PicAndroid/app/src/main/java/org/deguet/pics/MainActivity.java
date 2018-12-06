package org.deguet.pics;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.deguet.GlideUtil;
import org.deguet.ImageElement;
import org.deguet.ImageElementAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    public ImageElementAdapter adapter;
    public List<ImageElement> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ImageElementAdapter(this);
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);

        findViewById(R.id.gogallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotToGallery();
            }
        });
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToServer();
            }
        });
    }

    private void gotToGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void sendToServer() {
        for (ImageElement e : images){
            GlideUtil.sendIt(e);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && null != data) {
            // Get the Image from data
            images.clear();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            List<String> imagesEncodedList = new ArrayList<String>();
            if (data.getData() != null) {

                Uri uri = data.getData();
                ImageElement elt = new ImageElement(uri, this);
                images.add(elt);

            } else {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    images.clear();
                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        ImageElement elt = new ImageElement(uri, this);
                        images.add(elt);
                    }
                    Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                }

            }
            adapter.clear();
            adapter.addAll(images);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }
    }
}