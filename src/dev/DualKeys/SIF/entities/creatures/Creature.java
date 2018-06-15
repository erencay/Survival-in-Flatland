package dev.DualKeys.SIF.entities.creatures;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.entities.Entity;
import dev.DualKeys.SIF.tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEF_HEALTH = 100;
    public static final int DEF_HUNGER = 100;
    public static final int DEF_THIRST = 100;
    public static final float DEF_SPEED = 3.0f;
    public static final int DEF_WIDTH = 32,
                            DEF_HEIGHT = 32;

    protected Handler handler;
    protected int health, hunger, thirst;
    protected static int xp = 0, level;
    protected float speed;
    protected float xMove, yMove;
    public static int nextLvl;


    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.handler = handler;
        health = DEF_HEALTH;
        hunger = DEF_HUNGER;
        speed = DEF_SPEED;
        thirst = DEF_THIRST;
        nextLvl = 100;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        moveX();
        moveY();
    }

    public void moveX() {
        if (xMove > 0) {//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.WIDTH;

            if (!collision(tx, (int) (y + bounds.y) / Tile.HEIGHT) &&
                    !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)) {
                x += xMove;
            }
        } else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.WIDTH;

            if (!collision(tx, (int) (y + bounds.y) / Tile.HEIGHT) &&
                    !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)) {
                x += xMove;
            }
        }
    }

    public void moveY() {
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.HEIGHT;

            if (!collision((int) (x + bounds.x) / Tile.WIDTH, ty) && !collision((int) (x + bounds.x + bounds.width) / Tile.WIDTH, ty)) {
                y += yMove;
            }
        } else if (yMove > 0) {//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.HEIGHT;

            if (!collision((int) (x + bounds.x) / Tile.WIDTH, ty) && !collision((int) (x + bounds.x + bounds.width) / Tile.WIDTH, ty)) {
                y += yMove;
            }
        }
    }

    protected boolean collision(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public static int getXP() {
        return xp;
    }

    public static void setXP(int level) {
        xp = level;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int xp) {
        level = xp;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

}
