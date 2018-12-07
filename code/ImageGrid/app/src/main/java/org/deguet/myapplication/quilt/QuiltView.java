package org.deguet.myapplication.quilt;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.deguet.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class QuiltView extends FrameLayout {

    public QuiltViewBase quilt;
    public ViewGroup scroll;
    public int padding = 0;
    public boolean isVertical = false;
    public ArrayList<View> views;
    private Adapter adapter;

    public QuiltView(Context context,boolean isVertical) {
        super(context);
        this.isVertical = isVertical;
        setup();
    }

    public QuiltView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isVertical = true;
        setup();
    }

    public void setup(){
        views = new ArrayList<>();

        if(isVertical){
            scroll = new ScrollView(this.getContext());
        } else {
            scroll = new HorizontalScrollView(this.getContext());
        }
        quilt = new QuiltViewBase(getContext(), isVertical);
        scroll.addView(quilt);
        this.addView(scroll);

    }

    private void setViewsFromAdapter(Adapter adapter) {
        this.removeAllViews();
        for(int i = 0; i < adapter.getCount(); i++){
            quilt.addPatch(adapter.getView(i, null, quilt));
        }
    }

    public void addPatchImages(List<ImageView> images){

        for(ImageView image: images){
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            image.setLayoutParams(params);

            LinearLayout wrapper = new LinearLayout(this.getContext());
            wrapper.setPadding(padding, padding, padding, padding);
            wrapper.addView(image);
            quilt.addPatch(wrapper);
        }
    }

    public void setOrientation(boolean isVertical){
        this.isVertical = isVertical;
    }

}