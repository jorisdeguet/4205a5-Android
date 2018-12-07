package org.deguet.myapplication.packed;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.deguet.myapplication.Images;
import org.deguet.myapplication.R;
import org.deguet.myapplication.packed.QuiltPacker;
import org.deguet.myapplication.quilt.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * TODO the scroll view leads android to create all the layout to compute size.
 * This breaks memory as everything is loaded
 * This is solved by a recyclerview that loads only when nearly on screen
 */
public class QuiltPackedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilt_packed);
        List<String> urls = new ArrayList<String>(Arrays.asList(Images.imageThumbUrls));
        this.fillGrid((GridLayout) findViewById(R.id.grid1), urls, 200, 0.75);
        this.fillGrid((GridLayout) findViewById(R.id.grid2), urls, 300, 1.0);
    }


    private void fillGrid(GridLayout grid, List<String> urls, int minCellWidth, double heightWidthRatio) {
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int columnCount = width / minCellWidth;
        int cellWidth = width / columnCount;
        int cellHeight =  (int) (cellWidth * heightWidthRatio) ;
        grid.setColumnCount(columnCount);
        List<Tile> tiles = new ArrayList<>();
        Random r  = new Random(123);
        for (String url : urls) {
            // TODO get the image importance from the app (how many times it was liked etc)
            // make sure half of it is small so packing always works
            tiles.add(r.nextBoolean()?Tile.Small:r.nextBoolean()?Tile.Big:r.nextBoolean()?Tile.Tall:Tile.Flat);
            //tiles.add(Tile.Small);
        }
        QuiltPacker packer = new QuiltPacker(tiles, columnCount, true);
        packer.compute();

        RequestOptions options = RequestOptions
                .centerCropTransform()      // show the picture center but cuts what does not fit
                .placeholder(new ColorDrawable(Color.GREEN))     // ce qu'il y a en attendant l'image
                .error(new ColorDrawable(Color.RED));
        for (int i = 0 ; i < urls.size() ; i++) {
            final String url = urls.get(i);
            Tile tile = tiles.get(i);
            QuiltPacker.Position position = packer.position(i);
            ImageView iv = new ImageView(getApplicationContext());
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(QuiltPackedActivity.this, "url " + url, Toast.LENGTH_SHORT).show();
                }
            });
            Glide.with(this)
                    .load(url)
                    //.transition(withCrossFade())
                    .apply(options)
                    .into(iv);
            this.addCell(grid, cellWidth, cellHeight, iv, tile, position);
        }
    }

    /**
     * Handles the grid parameters (position + spans)
     * @param grid
     * @param cellWidth
     * @param cellHeight
     * @param view
     * @param tile
     * @param position
     */
    private void addCell(
            GridLayout grid,
            int cellWidth,
            int cellHeight,
            View view,
            Tile tile,
            QuiltPacker.Position position){
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = cellWidth* tile.col;
        params.height = cellHeight* tile.row;
        params.rowSpec = GridLayout.spec(position.r, tile.row);
        params.columnSpec = GridLayout.spec(position.c, tile.col);
        view.setLayoutParams(params);
        grid.addView(view);
    }
}
