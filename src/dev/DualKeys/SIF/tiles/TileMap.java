package dev.DualKeys.SIF.tiles;

public class TileMap {
    private int[][] tiles;
    private int size;

    public TileMap(int size) {
        this.tiles = new int[size][size];
        this.size = size;
    }

    public TileMap(int[][] tiles) {
        this.tiles = tiles;
        this.size = tiles.length;
    }

    public Tile getTile(int x, int y) {
        if (this.tiles[x][y] == 0) {
            return new Grass(0);
        } else if (this.tiles[x][y] == 1) {
            return new Dirt(1);
        } else if (this.tiles[x][y] == 2) {
            return new River(2);
        } else if (this.tiles[x][y] == 3) {
            return new Sand(3);
        } else if (this.tiles[x][y] == 4) {
            return new Pond(4);
        } else if (this.tiles[x][y] == 5) {
            return new FenceHoriz(5);
        } else if (this.tiles[x][y] == 6) {
            return new FenceVert(6);
        } else if (this.tiles[x][y] == 7) {
            return new FenceTL(7);
        } else if (this.tiles[x][y] == 8) {
            return new FenceTR(8);
        } else if (this.tiles[x][y] == 9) {
            return new FenceBL(9);
        } else {
            return this.tiles[x][y] == 10 ? new FenceBR(10) : null;
        }
    }

    public int[][] getTiles() {
        return this.tiles;
    }

    public int getSize() {
        return this.size;
    }
}