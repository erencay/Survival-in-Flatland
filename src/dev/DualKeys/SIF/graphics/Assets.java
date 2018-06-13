package dev.DualKeys.SIF.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int SIZE = 32;

    public static BufferedImage grass, dirt, river, sand, pond, tree, rock;
    public static BufferedImage fenceHoriz, fenceVert, fenceTL, fenceTR, fenceBL, fenceBR;
    public static BufferedImage start, startHover, title, player, zombie, woodUI;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;
    public static BufferedImage[] zombieDown, zombieUp, zombieLeft, zombieRight;

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/TileSheet.png"));
        SpriteSheet pSheet = new SpriteSheet(ImageLoader.loadImage("/Entity/EntitySheet_Player.png"));
        SpriteSheet zSheet = new SpriteSheet(ImageLoader.loadImage("/Entity/EntitySheet_Zombie.png"));

        // Blocks
        grass = sheet.crop(0, 0, SIZE, SIZE);
        dirt = sheet.crop(SIZE, 0, SIZE, SIZE);
        river = sheet.crop(SIZE * 2, 0, SIZE, SIZE);
        sand = sheet.crop(SIZE * 3, 0, SIZE, SIZE);
        pond = sheet.crop(SIZE * 4, 0, SIZE, SIZE);
        tree = sheet.crop(SIZE * 5, 14, SIZE, 50);
        rock = sheet.crop(SIZE * 4, SIZE, SIZE, SIZE);

        // Fence
        fenceHoriz = sheet.crop(SIZE * 6, 0, SIZE, SIZE);
        fenceVert = sheet.crop(SIZE * 7, 0, SIZE, SIZE);
        fenceTL = sheet.crop(SIZE * 8, 0, SIZE, SIZE);
        fenceTR = sheet.crop(SIZE * 9, 0, SIZE, SIZE);
        fenceBL = sheet.crop(SIZE * 8, SIZE, SIZE, SIZE);
        fenceBR = sheet.crop(SIZE * 9, SIZE, SIZE, SIZE);

        // UI
        woodUI = sheet.crop(0, SIZE * 3, SIZE, SIZE);
        title = sheet.crop(0, SIZE * 2, SIZE * 8, SIZE);
        start = sheet.crop(SIZE * 8, SIZE * 2, SIZE * 2, SIZE);
        startHover = sheet.crop(SIZE * 8, SIZE * 2, SIZE * 2, SIZE);

        // Player
        player = pSheet.crop(0, 0, SIZE, SIZE);

        playerDown = new BufferedImage[2];
        playerDown[0] = pSheet.crop(0, SIZE, SIZE, SIZE);
        playerDown[1] = pSheet.crop(0, SIZE * 2, SIZE, SIZE);

        playerUp = new BufferedImage[2];
        playerUp[0] = pSheet.crop(SIZE, SIZE, SIZE, SIZE);
        playerUp[1] = pSheet.crop(SIZE, SIZE * 2, SIZE, SIZE);

        playerLeft = new BufferedImage[2];
        playerLeft[0] = pSheet.crop(SIZE * 2, SIZE, SIZE, SIZE);
        playerLeft[1] = pSheet.crop(SIZE * 2, SIZE * 2, SIZE, SIZE);

        playerRight = new BufferedImage[2];
        playerRight[0] = pSheet.crop(SIZE * 3, SIZE, SIZE, SIZE);
        playerRight[1] = pSheet.crop(SIZE * 3, SIZE * 2, SIZE, SIZE);

        // Zombie
        zombie = zSheet.crop(0, 0, SIZE, SIZE);

        zombieDown = new BufferedImage[2];
        zombieDown[0] = zSheet.crop(0, SIZE, SIZE, SIZE);
        zombieDown[1] = zSheet.crop(0, SIZE * 2, SIZE, SIZE);

        zombieUp = new BufferedImage[2];
        zombieUp[0] = zSheet.crop(SIZE, SIZE, SIZE, SIZE);
        zombieUp[1] = zSheet.crop(SIZE, SIZE * 2, SIZE, SIZE);

        zombieLeft = new BufferedImage[2];
        zombieLeft[0] = zSheet.crop(SIZE * 2, SIZE, SIZE, SIZE);
        zombieLeft[1] = zSheet.crop(SIZE * 2, SIZE * 2, SIZE, SIZE);

        zombieRight = new BufferedImage[2];
        zombieRight[0] = zSheet.crop(SIZE * 3, SIZE, SIZE, SIZE);
        zombieRight[1] = zSheet.crop(SIZE * 3, SIZE * 2, SIZE, SIZE);
    }

}
