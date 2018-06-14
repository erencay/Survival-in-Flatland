package dev.DualKeys.SIF.worlds;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.tiles.Tile;
import dev.DualKeys.SIF.tiles.TileMap;
import dev.DualKeys.SIF.utils.Utils;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class World {
    private Handler handler;
    private int width;
    private int height;
    private int spawnX;
    private int spawnY;
    private int[][] tiles;
    private TileMap tileMap;

    public World(Handler handler, InputStream path, boolean isRandom) {
        this.handler = handler;
        this.loadWorld(path, isRandom);
    }

    public void update() {
    }

    public void render(Graphics g) {
        int xStart = (int)Math.max(0.0F, this.handler.getGameCamera().getxOffset() / 32.0F);
        int xEnd = (int)Math.min((float)this.width, (this.handler.getGameCamera().getxOffset() + (float)this.handler.getWidth()) / 32.0F + 1.0F);
        int yStart = (int)Math.max(0.0F, this.handler.getGameCamera().getyOffset() / 32.0F);
        int yEnd = (int)Math.min((float)this.height, (this.handler.getGameCamera().getyOffset() + (float)this.handler.getHeight()) / 32.0F + 1.0F);

        for(int y = yStart; y < yEnd; ++y) {
            for(int x = xStart; x < xEnd; ++x) {
                this.getTile(x, y).render(g, (int)((float)(x * 32) - this.handler.getGameCamera().getxOffset()), (int)((float)(y * 32) - this.handler.getGameCamera().getyOffset()));
            }
        }

    }

    public Tile getTile(int x, int y) {
        return this.tileMap.getTile(x, y);
    }

    private void loadWorld(InputStream path, boolean isRandom) {
        if (!isRandom) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(path));
                Throwable var4 = null;

                try {
                    String[] size = bufferedReader.readLine().split("\\s+");
                    this.width = Utils.parseInt(size[0]);
                    this.height = Utils.parseInt(size[1]);
                    ArrayList tokens = new ArrayList();

                    String line;
                    try {
                        while((line = bufferedReader.readLine()) != null) {
                            tokens.addAll(Arrays.asList(line.split("\\s+")));
                        }
                    } catch (IOException var18) {
                        var18.printStackTrace();
                    }

                    this.tiles = new int[this.width][this.height];

                    for(int y = 0; y < this.height; ++y) {
                        for(int x = 0; x < this.width; ++x) {
                            this.tiles[x][y] = Utils.parseInt((String)tokens.get(x + y * this.width));
                        }
                    }
                } catch (Throwable var19) {
                    var4 = var19;
                    throw var19;
                } finally {
                    if (bufferedReader != null) {
                        if (var4 != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable var17) {
                                var4.addSuppressed(var17);
                            }
                        } else {
                            bufferedReader.close();
                        }
                    }

                }
            } catch (IOException var21) {
                var21.printStackTrace();
            }
        } else {
            RandomWorldGenerator randomWorldGenerator = new RandomWorldGenerator();
            int[][] tokens1 = randomWorldGenerator.generateWorld(4, 25, -957976999);
            this.tileMap = new TileMap(tokens1);
            this.width = this.tileMap.getSize();
            this.height = this.tileMap.getSize();
        }

    }

    public int getSpawnX() {
        return this.spawnX;
    }

    public int getSpawnY() {
        return this.spawnY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}