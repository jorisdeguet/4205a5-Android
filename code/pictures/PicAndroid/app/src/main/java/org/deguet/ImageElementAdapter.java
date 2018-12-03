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
                sendIt(element);
            }
        });
        return v;
    }

    private void sendIt(ImageElement element) {
        try {
            if (useGlide) {
                final Target t = Glide.with(getContext()).as(byte[].class)
                        .load(element.uri)
                        .apply(
                                RequestOptions
                                        .encodeFormatOf(Bitmap.CompressFormat.JPEG)
                                        .encodeQuality(80)
                                        .override(1024, 1024)
                        )
                        .into(new SimpleTarget<byte[]>() {
                                  @Override
                                  public void onResourceReady(@NonNull byte[] resource, @Nullable Transition<? super byte[]> transition) {
                                      Log.i("UPLOAD Glide", "B64 " + resource.length);
                                      RequestBody bytes = RequestBody.create(MediaType.parse("application/octet-stream"), resource);
                                      HttpUtil.service().postBytes(bytes).enqueue(new Callback<Boolean>() {
                                          @Override
                                          public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                              Toast.makeText(getContext(), "Ok " + response.body(), Toast.LENGTH_SHORT).show();
                                          }

                                          @Override
                                          public void onFailure(Call<Boolean> call, Throwable t) {
                                              Toast.makeText(getContext(), "Ko", Toast.LENGTH_SHORT).show();
                                          }
                                      });
                                  }
                              }
                        );
                // see target doc https://bumptech.github.io/glide/javadocs/480/com/bumptech/glide/request/target/SimpleTarget.html
            } else {
                InputStream iStream = getContext().getContentResolver().openInputStream(element.uri);
                byte[] inputData = getBytes(iStream);
                File f = new File(getContext().getFilesDir(),"gna.jpg");

                OutputStream outputStream = new FileOutputStream(f);
                IOUtils.copy(iStream, outputStream);
                outputStream.close();
                final String b64  = Base64.encodeToString(inputData, Base64.DEFAULT);
                Log.i("UPLOAD", "B64 " + b64.length());
                HttpUtil.service().postBase64(b64).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Toast.makeText(getContext(), "Ok " + b64.length(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getContext(), "Ko", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        String res = "";
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        } else {
            return contentUri.getPath();
        }
        return res;
    }
}
