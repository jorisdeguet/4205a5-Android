package org.deguet.myapplication;

import org.deguet.myapplication.packed.QuiltPacker;
import org.deguet.myapplication.quilt.Tile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestPacker {

    @Test
    public void testPacker()
    {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);

        QuiltPacker packer = new QuiltPacker(tiles, 4, true);
        packer.compute();
    }

    @Test
    public void testPackerWide()
    {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);tiles.add(Tile.Big);
        tiles.add(Tile.Tall);
        tiles.add(Tile.Flat);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);
        tiles.add(Tile.Small);

        QuiltPacker packer = new QuiltPacker(tiles, 6, true);
        packer.compute();
    }

    @Test
    public void testPackerOnlySmalls()
    {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0 ; i < 99 ; i++) tiles.add(Tile.Small);

        QuiltPacker packer = new QuiltPacker(tiles, 5, true);
        packer.compute();
    }
}