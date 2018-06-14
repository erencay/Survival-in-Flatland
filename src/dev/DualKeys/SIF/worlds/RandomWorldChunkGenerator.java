package dev.DualKeys.SIF.worlds;

import java.util.Random;

/**
 * Represents generator of chunks
 */
public class RandomWorldChunkGenerator {
    // frequency of dirts
    private static final int DIRT_FREQUENCY = 25;
    // frequency of rivers
    private static final int RIVER_FREQUENCY = 50;
    private static RandomWorldChunkGenerator randomWorldChunkGenerator;

    private RandomWorldChunkGenerator() {
    }

    public static RandomWorldChunkGenerator getInstance() {
        return randomWorldChunkGenerator == null ? new RandomWorldChunkGenerator() : randomWorldChunkGenerator;
    }

    public WorldChunk generate(int chunkSize, int chunkSeed) {
        int[][] tokens = new int[chunkSize][chunkSize];
        tokens = this.generateDirtLayer(tokens, chunkSeed);
        tokens = this.generateRiverLayer(tokens, chunkSeed);
        tokens = this.generateSandLayer(tokens);
        return new WorldChunk(tokens, chunkSize, chunkSeed);
    }

    /**
     * Generates dirts
     * @param tokens
     * @param chunkSeed
     * @return
     */
    private int[][] generateDirtLayer(int[][] tokens, int chunkSeed) {
        Random random = new Random((long)chunkSeed);

        for(int i = 0; i <= DIRT_FREQUENCY; ++i) {
            int x = random.nextInt(tokens.length);
            int y = random.nextInt(tokens.length);
            if (tokens[x][y] == 0) {
                tokens[x][y] = 1;
                int direction = random.nextInt(8);
                if (direction == RandomWorldChunkGenerator.Direction.TOP.getValue()) {
                    if (x - 1 >= 0) {
                        --x;
                        tokens[x][y] = 1;
                        System.out.println("top");
                    }
                } else {
                    int[] var10000;
                    if (direction == RandomWorldChunkGenerator.Direction.LEFT.getValue()) {
                        if (y - 1 >= 0) {
                            var10000 = tokens[x];
                            --y;
                            var10000[y] = 1;
                            System.out.println("left");
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM.getValue()) {
                        if (x + 1 < tokens.length) {
                            ++x;
                            tokens[x][y] = 1;
                            System.out.println("bottom");
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.RIGHT.getValue()) {
                        if (y + 1 < tokens.length) {
                            var10000 = tokens[x];
                            ++y;
                            var10000[y] = 1;
                            System.out.println("right");
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.TOP_LEFT.getValue()) {
                        if (x - 1 >= 0 && y - 1 >= 0) {
                            --x;
                            var10000 = tokens[x];
                            --y;
                            var10000[y] = 1;
                            System.out.println("top left");
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.TOP_RIGHT.getValue()) {
                        if (x - 1 >= 0 && y + 1 < tokens.length) {
                            --x;
                            var10000 = tokens[x];
                            ++y;
                            var10000[y] = 1;
                            System.out.println("top right");
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM_LEFT.getValue()) {
                        if (x + 1 < tokens.length && y - 1 >= 0) {
                            ++x;
                            var10000 = tokens[x];
                            --y;
                            var10000[y] = 1;
                            System.out.println("bottom left");
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM_RIGHT.getValue() && x + 1 < tokens.length && y + 1 < tokens.length) {
                        ++x;
                        var10000 = tokens[x];
                        ++y;
                        var10000[y] = 1;
                        System.out.println("bottom right");
                    }
                }
            }
        }

        return tokens;
    }

    /**
     * Generates rivers
     * @param tokens
     * @param chunkSeed
     * @return
     */
    private int[][] generateRiverLayer(int[][] tokens, int chunkSeed) {
        Random random = new Random((long)chunkSeed);
        int row = random.nextInt(tokens.length);
        int col = random.nextInt(tokens.length);
        tokens[row][col] = 2;

        for(int i = 0; i <= RIVER_FREQUENCY; ++i) {
            int direction = random.nextInt(8);
            if (direction == RandomWorldChunkGenerator.Direction.TOP.getValue()) {
                if (row - 1 >= 0) {
                    tokens[row - 1][col] = 2;
                    --row;
                    System.out.println("top");
                }
            } else if (direction == RandomWorldChunkGenerator.Direction.LEFT.getValue()) {
                if (col - 1 >= 0) {
                    tokens[row][col - 1] = 2;
                    --col;
                    System.out.println("left");
                }
            } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM.getValue()) {
                if (row + 1 < tokens.length) {
                    tokens[row + 1][col] = 2;
                    ++row;
                    System.out.println("bottom");
                }
            } else if (direction == RandomWorldChunkGenerator.Direction.RIGHT.getValue()) {
                if (col + 1 < tokens.length) {
                    tokens[row][col + 1] = 2;
                    ++col;
                    System.out.println("right");
                }
            } else if (direction == RandomWorldChunkGenerator.Direction.TOP_LEFT.getValue()) {
                if (row - 1 >= 0 && col - 1 >= 0) {
                    tokens[row - 1][col - 1] = 2;
                    --row;
                    --col;
                    System.out.println("top left");
                }
            } else if (direction == RandomWorldChunkGenerator.Direction.TOP_RIGHT.getValue()) {
                if (row - 1 >= 0 && col + 1 < tokens.length) {
                    tokens[row - 1][col + 1] = 2;
                    --row;
                    ++col;
                    System.out.println("top right");
                }
            } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM_LEFT.getValue()) {
                if (row + 1 < tokens.length && col - 1 >= 0) {
                    tokens[row + 1][col - 1] = 2;
                    ++row;
                    --col;
                    System.out.println("bottom left");
                }
            } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM_RIGHT.getValue() && row + 1 < tokens.length && col + 1 < tokens.length) {
                tokens[row + 1][col + 1] = 2;
                ++row;
                ++col;
                System.out.println("bottom right");
            }
        }

        int[][] shift = this.shift(tokens, 1, RandomWorldChunkGenerator.Direction.LEFT);
        return this.mergeTokens(tokens, shift);
    }

    /**
     * Shifts the chunk to the specified value and direction
     * @param tokens
     * @param shiftValue
     * @param direction
     * @return
     */
    private int[][] shift(int[][] tokens, int shiftValue, RandomWorldChunkGenerator.Direction direction) {
        int[][] temp = new int[tokens.length][tokens.length];

        for(int i = 0; i < tokens.length; ++i) {
            for(int j = 0; j < tokens[i].length; ++j) {
                if (tokens[i][j] != 0) {
                    if (direction == RandomWorldChunkGenerator.Direction.TOP) {
                        if (i - shiftValue >= 0) {
                            temp[i - shiftValue][j] = tokens[i][j];
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.RIGHT) {
                        if (j + shiftValue < tokens[i].length) {
                            temp[i][j + shiftValue] = tokens[i][j];
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.LEFT) {
                        if (j - shiftValue >= 0) {
                            temp[i][j - shiftValue] = tokens[i][j];
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM) {
                        if (i + shiftValue < tokens.length) {
                            temp[i + shiftValue][j] = tokens[i][j];
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.TOP_RIGHT) {
                        if (i - shiftValue < tokens.length && j + shiftValue < tokens[i].length) {
                            temp[i - shiftValue][j + shiftValue] = tokens[i][j];
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.TOP_LEFT) {
                        if (i - shiftValue < tokens.length && j - shiftValue >= 0) {
                            temp[i - shiftValue][j - shiftValue] = tokens[i][j];
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM_RIGHT) {
                        if (i + shiftValue < tokens.length && j + shiftValue < tokens[i].length) {
                            temp[i + shiftValue][j + shiftValue] = tokens[i][j];
                        }
                    } else if (direction == RandomWorldChunkGenerator.Direction.BOTTOM_LEFT && i + shiftValue < tokens.length && j - shiftValue >= 0) {
                        temp[i + shiftValue][j - shiftValue] = tokens[i][j];
                    }
                }
            }
        }

        return temp;
    }

    /**
     * Puts one layer of chunk on another
     * @param destination
     * @param source
     * @return
     */
    private int[][] mergeTokens(int[][] destination, int[][] source) {
        if (destination.length != source.length) {
            return new int[0][];
        } else {
            for(int i = 0; i < destination.length; ++i) {
                for(int j = 0; j < destination[i].length; ++j) {
                    if (source[i][j] != 0) {
                        destination[i][j] = source[i][j];
                    }
                }
            }

            return destination;
        }
    }

    /**
     * Generates send layer
     * @param tokens
     * @return
     */
    private int[][] generateSandLayer(int[][] tokens) {
        for(int i = 0; i < tokens.length; ++i) {
            for(int j = 0; j < tokens[i].length; ++j) {
                if (tokens[i][j] == 2) {
                    if (j + 1 < tokens[i].length && tokens[i][j + 1] != 2) {
                        tokens[i][j + 1] = 3;
                    }

                    if (i + 1 < tokens.length && tokens[i + 1][j] != 2) {
                        tokens[i + 1][j] = 3;
                    }

                    if (i + 1 < tokens.length && j + 1 < tokens[i].length && tokens[i + 1][j + 1] != 2) {
                        tokens[i + 1][j + 1] = 3;
                    }

                    if (j - 1 >= 0 && tokens[i][j - 1] != 2) {
                        tokens[i][j - 1] = 3;
                    }

                    if (i - 1 >= 0 && tokens[i - 1][j] != 2) {
                        tokens[i - 1][j] = 3;
                    }

                    if (i - 1 >= 0 && j - 1 >= 0 && tokens[i - 1][j - 1] != 2) {
                        tokens[i - 1][j - 1] = 3;
                    }
                }
            }
        }

        return tokens;
    }

    /**
     * Represents direction of shift
     */
    private enum Direction {
        TOP(0),
        LEFT(1),
        BOTTOM(2),
        RIGHT(3),
        TOP_LEFT(4),
        TOP_RIGHT(5),
        BOTTOM_LEFT(6),
        BOTTOM_RIGHT(7);

        private int value;

        Direction(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}