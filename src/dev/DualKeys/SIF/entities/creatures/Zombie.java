package dev.DualKeys.SIF.entities.creatures;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.graphics.Animation;
import dev.DualKeys.SIF.graphics.Assets;
import dev.DualKeys.SIF.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Zombie extends Creature {

    private static Handler handler;
    private static Player player;

    private Animation up, down, left, right;

    public Zombie(Handler handler, Player player, float x, float y) {
        super(handler, x, y, Creature.DEF_WIDTH, Creature.DEF_HEIGHT);
        this.player = player;
        this.handler = handler;
        this.setSpeed(2.5f);

        down = new Animation(500, Assets.zombieDown);
        up = new Animation(500, Assets.zombieUp);
        left = new Animation(500, Assets.zombieLeft);
        right = new Animation(500, Assets.zombieRight);
    }

    @Override
    public void update() {
        getInput();
    }

    public void getInput() {
        if (inRange((int)player.getY(), (int)y) <= Tile.WIDTH * 5 && inRange((int)player.getX(), (int)x) <= Tile.WIDTH * 5) {
            if (player.getY() < y + 18) {
                y -= speed;
            }
            if (player.getY() > y - 18) {
                y += speed;
            }
            if (player.getX() < x + 18) {
                x -= speed;
            }
            if (player.getX() > x - 18) {
                x += speed;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
    }

    private BufferedImage currentAnimation() {
        if (player.getX() <= x) {
            return left.currentFrame();
        } else if (player.getX() > x) {
            return right.currentFrame();
        } else if (player.getY() < y) {
            return up.currentFrame();
        } else if (player.getY() > y) {
            return down.currentFrame();
        } else {
            return Assets.zombie;
        }
    }

    private int inRange(int x, int y) {
        if (x < y) {
            return y - x;
        } else if (x > y) {
            return x - y;
        } else {
            return 0;
        }
    }

}
