package dev.DualKeys.SIF.worlds;

import dev.DualKeys.SIF.Game;
import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.tiles.Tile;
import dev.DualKeys.SIF.utils.Utils;
import java.awt.Graphics;

public class World {

    private Handler handler;
    private int width, height;
    public static int spawnX, spawnY;
    private int[][] tiles;

    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
    }

    public void update() {

    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int)(x * Tile.WIDTH - handler.getGameCamera().getxOffset()), (int)(y * Tile.HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.grass;
        }
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
            }
        }
    }

    public static int getSpawnX() {
        return spawnX;
    }

    public static int getSpawnY() {
        return spawnY;
    }

}
