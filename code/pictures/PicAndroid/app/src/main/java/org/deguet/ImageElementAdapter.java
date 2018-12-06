package org.deguet;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.media.ExifInterface;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;


import org.apache.commons.io.IOUtils;
import org.deguet.pics.HttpUtil;
import org.deguet.pics.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageElementAdapter extends ArrayAdapter<ImageElement> {

    public boolean useGlide = true;

    public ImageElementAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View v = li.inflate(R.layout.item, null);
        final ImageElement element = getItem(position);
        final ImageView iv =  v.findViewById(R.id.iv);
        TextView tv =  v.findViewById(R.id.tv);
        TextView exif =  v.findViewById(R.id.exif);

        tv.setText(element.uri.toString());
        try {
            ExifInterface ei = element.getExif();
            String exifString =
                    Arrays.toString(ei.getLatLong()) +
                            ei.getAttribute(ExifInterface.TAG_DATETIME);
            exif.setText(exifString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Glide.with(v).load(element.uri).into(iv);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideUtil.sendIt(element);
            }
        });
        return v;
    }

}
