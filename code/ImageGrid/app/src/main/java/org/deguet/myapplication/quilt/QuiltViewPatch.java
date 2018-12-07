package org.deguet.myapplication.quilt;


import java.util.Random;

public class QuiltViewPatch {
    public int width_ratio;
    public int height_ratio;


    public static Random r = new Random(123);


    private QuiltViewPatch(int width_ratio, int height_ratio){
        this.width_ratio = width_ratio;
        this.height_ratio = height_ratio;
    }

    private static QuiltViewPatch create(Tile tile){
        switch(tile){
            case Big:
                return new QuiltViewPatch(2,2);
            case Small:
                return new QuiltViewPatch(1,1);
            case Tall:
                return new QuiltViewPatch(1,2);
            case Flat:
                return new QuiltViewPatch(2,1);
        }

        return new QuiltViewPatch(1,1);
    }


    public static QuiltViewPatch init(int position, int column){
        float p = r.nextFloat();
        if (p < 0.5) return create(Tile.Small);
        if (p < 0.75) return create(Tile.Tall);
        if (p < 0.9) return create(Tile.Big);
        return create(Tile.Flat);
    }

}