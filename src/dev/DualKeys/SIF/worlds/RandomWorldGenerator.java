package dev.DualKeys.SIF.worlds;

import java.util.Arrays;
import java.util.Random;

public class RandomWorldGenerator {

    public RandomWorldGenerator() {}

    public int[][] generateWorld(int chunkCount, int chunkSize, long worldSeed) {
        Random random = new Random(worldSeed);
        RandomWorldChunkGenerator randomWorldChunkGenerator = RandomWorldChunkGenerator.getInstance();
        int[][] worldTemp = new int[0][];

        for (int i = 0; i < chunkCount; i++) {
            int[][] tokens = randomWorldChunkGenerator.generate(chunkSize, random.nextInt()).getTokens();
            if (i == 0) {
                worldTemp = tokens;
            } else {
                worldTemp = this.combineChunks(worldTemp, tokens);
            }
        }

        return worldTemp;
    }

    private int[][] combineChunks(int[][] chunk1, int[][] chunk2) {
        int[][] temp = new int[chunk1.length][chunk1.length * 2];

        for (int i = 0; i < chunk1.length; i++) {
            temp[i] = Arrays.copyOf(chunk1[i], chunk1[i].length + chunk2[i].length);
            System.arraycopy(chunk2[i], 0, temp[i], chunk1[i].length, chunk2[i].length);
        }
        return temp;
    }

}