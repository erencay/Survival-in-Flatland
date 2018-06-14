package dev.DualKeys.SIF.entities.creatures;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.graphics.Animation;
import dev.DualKeys.SIF.graphics.Assets;
import dev.DualKeys.SIF.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Handler handler;
    private Animation up, down, left, right;
    private boolean swimming;

    public Player(Handler handler, int x, int y) {
        super(handler, x, y, Creature.DEF_WIDTH, Creature.DEF_HEIGHT);
        this.handler = handler;
        
        down = new Animation(500, Assets.playerDown);
        up = new Animation(500, Assets.playerUp);
        left = new Animation(500, Assets.playerLeft);
        right = new Animation(500, Assets.playerRight);

        bounds.x = 8;
        bounds.y = 4;
        bounds.width = 16;
        bounds.height = 28;
    }

    @Override
    public void update() {
        down.update();
        up.update();
        left.update();
        right.update();
        if (handler.getKeyManager().run && swimming) {
            setSpeed(2);
        } else if (swimming) {
            setSpeed(1);
        } else if (handler.getKeyManager().run) {
            setSpeed(4);
        } else {
            setSpeed(3);
        }
        getInput();
        move();
        handler.getGameCamera().centerOn(this);
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
        if (handler.getWorld().getTile((int)x / Tile.WIDTH, (int)y / Tile.HEIGHT).getId() == 4 ||
            handler.getWorld().getTile((int)x / Tile.WIDTH, (int)y / Tile.HEIGHT).getId() == 2) {
            swimming = true;
        } else {
            swimming = false;
        }
        g.setColor(Color.red);
        g.drawRect((int)(bounds.x + x - handler.getGameCamera().getxOffset()),
                    (int)(bounds.y + y - handler.getGameCamera().getyOffset()),
                    bounds.width,
                    bounds.height);
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
