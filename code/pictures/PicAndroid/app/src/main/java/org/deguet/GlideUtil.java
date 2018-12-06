package org.deguet;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import org.deguet.pics.HttpUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlideUtil {

    public static void sendIt(final ImageElement e){
        final Target t = Glide.with(e.ctx).as(byte[].class)
                .load(e.uri)
                .apply(
                        RequestOptions
                                .encodeFormatOf(Bitmap.CompressFormat.JPEG)
                                .encodeQuality(80)
                                .override(1024, 1024)
                )
                .into(new SimpleTarget<byte[]>() {
                          @Override
                          public void onResourceReady(@NonNull byte[] resource, @Nullable Transition<? super byte[]> transition) {
                              Log.i("UPLOAD Glide", "binary length " + resource.length);
                              RequestBody bytes = RequestBody.create(MediaType.parse("application/octet-stream"), resource);
                              HttpUtil.service().postBytes(bytes).enqueue(new Callback<Boolean>() {
                                  @Override
                                  public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                      Toast.makeText(e.ctx, "Ok " + response.body(), Toast.LENGTH_SHORT).show();
                                  }

                                  @Override
                                  public void onFailure(Call<Boolean> call, Throwable t) {
                                      Toast.makeText(e.ctx, "Ko", Toast.LENGTH_SHORT).show();
                                  }
                              });
                          }
                      }
                );
    }

}
