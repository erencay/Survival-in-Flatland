package dev.DualKeys.SIF.entities.creatures;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.entities.Entity;

public abstract class Creature extends Entity {

    public static final int DEF_HEALTH = 100;
    public static final int DEF_HUNGER = 100;
    public static final float DEF_SPEED = 3.0f;
    public static final int DEF_WIDTH = 32,
                            DEF_HEIGHT = 32;

    protected Handler handler;
    protected int health, hunger;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.handler = handler;
        health = DEF_HEALTH;
        hunger = DEF_HUNGER;
        speed = DEF_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        x += xMove;
        y += yMove;
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
    
}
