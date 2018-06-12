package dev.DualKeys.SIF.entities.creatures;

import java.awt.Graphics;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.graphics.Animation;
import dev.DualKeys.SIF.graphics.Assets;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Handler handler;
    private Animation up, down, left, right;

    public Player(Handler handler, int x, int y) {
        super(x, y, Creature.DEF_WIDTH, Creature.DEF_HEIGHT);
        this.handler = handler;
        
        down = new Animation(500, Assets.playerDown);
        up = new Animation(500, Assets.playerUp);
        left = new Animation(500, Assets.playerLeft);
        right = new Animation(500, Assets.playerRight);
    }

    @Override
    public void update() {
        down.update();
        up.update();
        left.update();
        right.update();
        getInput();
        move();
        handler.getGameCamera().centerOn(this);
        handler.getGameCamera().checkBlankSpace();
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;
        
        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
        if (handler.getKeyManager().food) {
            hunger += 10;
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), null);
    }

    private BufferedImage currentAnimation() {
        if (handler.getKeyManager().left) {
            return left.currentFrame();
        } else if (handler.getKeyManager().right) {
            return right.currentFrame();
        } else if (handler.getKeyManager().up) {
            return up.currentFrame();
        } else if (handler.getKeyManager().down) {
            return down.currentFrame();
        } else {
            return Assets.player;
        }
    }

}
