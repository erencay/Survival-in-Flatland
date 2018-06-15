package dev.DualKeys.SIF.worlds;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.tiles.Tile;
import dev.DualKeys.SIF.tiles.TileMap;
import dev.DualKeys.SIF.utils.Utils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class World {
    private Handler handler;
    private int width;
    private int height;
    private int[][] tiles;
    private TileMap tileMap;

    public World(Handler handler, InputStream path, boolean isRandom) {
        this.handler = handler;
        this.loadWorld(path, isRandom);
    }

    public void update() {
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0.0F, this.handler.getGameCamera().getxOffset() / Tile.WIDTH);
        int xEnd = (int) Math.min(this.width, (this.handler.getGameCamera().getxOffset() + this.handler.getWidth()) / Tile.WIDTH + 1.0F);
        int yStart = (int) Math.max(0.0F, this.handler.getGameCamera().getyOffset() / Tile.HEIGHT);
        int yEnd = (int) Math.min(this.height, (this.handler.getGameCamera().getyOffset() + this.handler.getHeight()) / Tile.WIDTH + 1.0F);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                this.getTile(x, y).render(g, (int) ((x * Tile.WIDTH) - this.handler.getGameCamera().getxOffset()), (int) ((y * Tile.HEIGHT) - this.handler.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y) {
        return this.tileMap.getTile(x, y);
    }

    private void loadWorld(InputStream path, boolean isRandom) {
        if (isRandom) {
            Random random = new Random();
            int seed = random.nextInt();
            OpenSimplexNoise noise = new OpenSimplexNoise(seed);
            int[][] tokens = new int[1024][1024];
            for (int y = 0; y < tokens.length; y++) {
                for (int x = 0; x < tokens[y].length; x++) {
                    double noiseVal = noise.eval(x / 30d, y / 30d, 0);
                    if (noiseVal > -0.1 && noiseVal < 0) {
                        tokens[x][y] = 3;
                    } else if (noiseVal <= -0.1){
                        tokens[x][y] = 2;
                    } else if (noiseVal >= 0 /*&& noiseVal <= 0.5*/){
                        tokens[x][y] = 0;
                    }
                    if (noiseVal >= 0.6 && noiseVal <= 0.9) {
                        tokens[x][y] = 1;
                    }
                }
            }
            File file = new File("./Worlds/random.world");

            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                for (int i = 0; i < tokens.length; i++) {
                    for (int j = 0; j < tokens[i].length; j++) {
                        bufferedWriter.append(String.valueOf(tokens[j][i])).append(" ");
                    }
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(path))) {
            String[] size = bufferedReader.readLine().split("\\s+");
            this.width = Utils.parseInt(size[0]);
            this.height = Utils.parseInt(size[1]);

            List<String> tokens = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                tokens.addAll(Arrays.asList(line.split("\\s+")));
            }

            this.tiles = new int[this.width][this.height];

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    this.tiles[x][y] = Utils.parseInt(tokens.get(x + y * this.width));
                }
            }
            this.tileMap = new TileMap(tiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}