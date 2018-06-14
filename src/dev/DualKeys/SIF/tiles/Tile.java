package dev.DualKeys.SIF.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

//    public static Tile[] tiles = new Tile[256];
//    public static Tile grass = new Grass(0);
//    public static Tile dirt = new Dirt(1);
//    public static Tile river = new River(2);
//    public static Tile sand = new Sand(3);
//    public static Tile pond = new Pond(4);
//    public static Tile fenceHoriz = new FenceHoriz(5);
//    public static Tile fenceVert = new FenceVert(6);
//    public static Tile fenceTL = new FenceTL(7);
//    public static Tile fenceTR = new FenceTR(8);
//    public static Tile fenceBL = new FenceBL(9);
//    public static Tile fenceBR = new FenceBR(10);

    public static final int WIDTH = 32,
                            HEIGHT = 32;
    protected final BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        //tiles[id] = this;
    }

    public void update() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

}
