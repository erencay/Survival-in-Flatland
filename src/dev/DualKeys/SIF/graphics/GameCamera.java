package dev.DualKeys.SIF.graphics;

import dev.DualKeys.SIF.Game;
import dev.DualKeys.SIF.entities.Entity;

public class GameCamera {

    private Game game;
    private float xOffset, yOffset;
    
    public GameCamera(Game game, float xOffset, float yOffset) {
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        }
    }
    
    public void centerOn(Entity e) {
        xOffset = e.getX() - game.width / 2 + e.getWidth() / 2;
        yOffset = e.getY() - game.height / 2 + e.getHeight() / 2;
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