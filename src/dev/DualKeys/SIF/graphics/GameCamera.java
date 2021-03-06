package dev.DualKeys.SIF.graphics;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.entities.Entity;
import dev.DualKeys.SIF.tiles.Tile;

public class GameCamera {

    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getWorld().getWidth() * Tile.WIDTH - (handler.getWidth())) {
            xOffset = handler.getWorld().getWidth() * Tile.WIDTH - (handler.getWidth());
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getWorld().getHeight() * Tile.HEIGHT - (handler.getHeight() - 64)) {
            yOffset = handler.getWorld().getHeight() * Tile.HEIGHT - (handler.getHeight() - 64);
        }
    }

    public void centerOn(Entity e) {
        xOffset = e.getX() - (handler.getWidth() / 2) - (e.getWidth() / 2) + 16;
        yOffset = e.getY() - (handler.getHeight() / 2) - (e.getHeight() / 2) + 32;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
