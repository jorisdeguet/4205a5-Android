package org.deguet.myapplication.quilt;

public enum Tile {

    Big (2,2),
    Small (1,1),
    Tall (1,2),
    Flat (2,1);

    public int col, row, area;

    Tile(int col, int row){
        this.col = col;
        this.row = row;
        this.area = col * row;
    }

}
