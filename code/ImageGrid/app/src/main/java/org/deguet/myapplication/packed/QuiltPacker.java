package org.deguet.myapplication.packed;

import org.deguet.myapplication.quilt.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Pure java algorithm to pack rectangular tiles inside a n wide rectangle
 *
 * Works by placing larger tiles first and smaller last by picking available position at random.
 */

public class QuiltPacker {

    private static class InternalTile {
        @Override
        public String toString() {
            return "{" +
                    "index=" + initialIndex +
                    ",s=" + s +
                    '}';
        }

        int initialIndex;
        Tile s;
    }

    private boolean random;
    private int rows;
    private int columns;
    private int totalUnits;
    private List<InternalTile> tiles = new ArrayList<>();

    private Integer[][] tiling;

    public QuiltPacker(List<Tile> tiles, int columnCount, boolean random){
        this.random = random;
        for (int i = 0 ; i < tiles.size() ; i++) {
            Tile original = tiles.get(i);
            InternalTile internal = new InternalTile();
            internal.initialIndex = i;
            internal.s = original;
            this.tiles.add(internal);
        }
        this.columns = columnCount;
        totalUnits = 0 ;
        for (Tile tile : tiles){
            totalUnits += tile.col * tile.row;
        }
        rows = (totalUnits / columns) + (totalUnits % columnCount == 0 ? 0 : 1); // add one line if it is not exact
    }


    public void compute() {
        List<InternalTile> sorted = new ArrayList<>(tiles);
        Collections.sort(sorted, comp());
        for (int seed = 0 ; seed < 100 ; seed++ ) {
            try {
                Random r = new Random(seed);
                System.out.println("Try at  " + seed);
                this.tiling = tryWithSeed(rows, columns, sorted, r);
                return;
            }catch(Throwable t) {}
        }
        // last chance attempt with no randomness
        this.tiling = tryWithSeed(rows, columns, tiles, null);
    }

    private Integer[][] tryWithSeed(int rows, int cols, List<InternalTile> sorted, Random r){
        Integer[][] tiling = new Integer[rows][cols];

        for (int index = 0 ; index < sorted.size() ; index++) {
            InternalTile t = sorted.get(index);
            // find a spot for it
            Position spot = findASpot(tiling, t, r);
            takeASpot(spot, t, tiling);
        }
        System.out.println(sorted);
        show(tiling);
        return tiling;
    }

    private void takeASpot(Position p, InternalTile tile, Integer[][] tiling) {
        switch (tile.s) {
            case Small: {
                tiling[p.r][p.c] = tile.initialIndex;
                return;
            }
            case Flat: {
                tiling[p.r][p.c] = tile.initialIndex;
                tiling[p.r][p.c +1] = tile.initialIndex;
                return;
            }
            case Tall: {
                tiling[p.r][p.c] = tile.initialIndex;
                tiling[p.r +1][p.c] = tile.initialIndex;
                return;
            }
            case Big: {
                tiling[p.r][p.c] = tile.initialIndex;
                tiling[p.r][p.c +1] = tile.initialIndex;
                tiling[p.r +1][p.c] = tile.initialIndex;
                tiling[p.r +1][p.c +1] = tile.initialIndex;
                return;
            }
        }
    }

    private Position findASpot(Integer[][] result, InternalTile t, Random r) {
        List<Position> free = new ArrayList<>();
        for(int x= 0 ; x < rows ; x++){
            for(int y= 0 ; y < columns ; y++) {
                if (result[x][y] == null) free.add(new Position(x,y));
            }
        }
        if (random && r != null) Collections.shuffle(free, r);
        for (Position p : free) {
            // as soon as it fits in, we are good
            switch (t.s) {
                case Small: return p;
                case Flat: {
                    if (isFree(p.r,p.c +1, result)) {
                        return p;
                    }
                    break;
                }
                case Tall: {
                    if (isFree(p.r +1,p.c, result)) {
                        return p;
                    }
                    break;
                }
                case Big: {
                    if (isFree(p.r +1,p.c, result)
                            && isFree(p.r,p.c +1, result)
                            && isFree(p.r +1,p.c +1, result)) {
                        return p;
                    }
                    break;
                }
            }
        }
        throw new IllegalArgumentException("No free");
    }

    private boolean isFree(int r, int c, Integer[][] tiling) {
        if (c >= this.columns) return false;
        if (r >= this.rows) return false;
        if (tiling[r][c] != null) return false;
        return true;
    }

    private static Comparator<InternalTile> comp() {
        return new Comparator<InternalTile>() {
            @Override
            public int compare(InternalTile a, InternalTile b) {
                return b.s.row*b.s.col - a.s.row*a.s.col;
            }
        };
    }

    public static void show(Integer[][] packing){
        for(int x= 0 ; x < packing.length ; x++){
            for(int y= 0 ; y < packing[0].length ; y++){
                Integer t = packing[x][y];
                String output = String.format("%1$5s", ""+t);
                System.out.print(output);
            }
            System.out.println();
        }
    }

    public Position position(int i) {
        for(int r = 0 ; r < rows ; r++){
            for(int c = 0 ; c < columns ; c++){
                if (tiling[r][c] != null && tiling[r][c] == i) return new Position(r,c);
            }
        }
        throw new IllegalArgumentException("NoSuchTile");
    }


    public static class Position {
        public Position(int x, int y) { this.r = x; this.c = y; }
        public int r, c;

        @Override
        public boolean equals(Object o) {
            Position position = (Position) o;
            return r == position.r && c == position.c;
        }

        @Override
        public int hashCode() {return r;}
    }
}
