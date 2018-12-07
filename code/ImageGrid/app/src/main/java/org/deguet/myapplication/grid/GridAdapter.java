package org.deguet.myapplication.grid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.deguet.myapplication.stagger.ImagesAdapter;

import java.util.Random;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    Context context;
    String[] urls;
    public GridAdapter(Context context, String[] urls) {
        this.context = context;
        this.urls = urls;
    }
    @Override
    public GridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        ImageView iv = new ImageView(context);
        //iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100 + new Random().nextInt(200)));
        View v = iv;
        // set the view's size, margins, paddings and layout parameters
        GridAdapter.MyViewHolder vh = new GridAdapter.MyViewHolder(v); // pass the view to View Holder
        vh.image = iv;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.MyViewHolder holder, int position) {
        // set the data in items
        RequestOptions options  = new RequestOptions()
                .placeholderOf(android.R.drawable.btn_minus)
                .centerCrop();
        String url = this.urls[position];
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(holder.image);
        //holder.image.getLayoutParams().height = 200;

    }

    @Override
    public int getItemCount() {
        return urls.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}