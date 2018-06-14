package dev.DualKeys.SIF.worlds;

import java.util.Arrays;

/**
 * Represents chunk of the world
 */
public class WorldChunk {
    private final int[][] tokens;
    private final int chunkSize;
    private final long chunkSeed;

    public WorldChunk(int[][] tokens, int chunkSize, long chunkSeed) {
        this.tokens = tokens;
        this.chunkSize = chunkSize;
        this.chunkSeed = chunkSeed;
    }

    public int[][] getTokens() {
        return this.tokens;
    }

    public int getChunkSize() {
        return this.tokens.length;
    }

    public String toString() {
        return "WorldChunk{tokens=" + Arrays.deepToString(this.tokens) + ", chunkSize=" + this.chunkSize + ", chunkSeed=" + this.chunkSeed + '}';
    }
}