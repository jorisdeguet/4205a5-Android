package org.deguet;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.media.ExifInterface;

import java.io.IOException;
import java.io.InputStream;

public class ImageElement {



    public Uri uri;
    Context ctx;

    @Override
    public String toString() {
        return "ImageElement{" +
                "uri=" + uri +
                '}';
    }

    public ImageElement(Uri uri, Context ctx) {
        this.uri = uri;
        this.ctx = ctx;
    }

    public Bitmap getBitmap() throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), uri);
        return bitmap;
    }

    public ExifInterface getExif() throws IOException {
        InputStream in = ctx.getContentResolver().openInputStream(uri);
        ExifInterface exifInterface = new ExifInterface(in);
        return exifInterface;
    }
}
