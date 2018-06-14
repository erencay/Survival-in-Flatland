package dev.DualKeys.SIF.UI;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.entities.creatures.Player;
import dev.DualKeys.SIF.graphics.Assets;

import java.awt.*;

public class GUI {

    private static final int MAX_HEALTH = 100;
    private static final int MAX_HUNGER = 100;
    
    private Player player;
    private Handler handler;

    private Font hFont;
    
    private int speed, hSpeed;
    private int health, hunger, fps;
    private long time;
    private int days;
    private long lastTime, timer, hTimer, dTimer;
    
    public GUI(Handler handler, Player player) {
        this.player = player;
        this.handler = handler;
        hFont = new Font("Helvetica", Font.BOLD, 16);
        speed = 1000;
        hSpeed = 5000;
        timer = 0;
        hTimer = 0;
        dTimer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public void update() {
        health = player.getHealth();
        hunger = player.getHunger();

        timer += System.currentTimeMillis() - lastTime;
        hTimer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (handler.getState() == handler.getGameState()) {
            if (timer > speed) {
                timer = 0;
                fps = handler.getFPS();
                if (health < MAX_HEALTH && hunger >= 85) {
                    health++;
                }
            }

            if (hTimer > hSpeed) {
                hTimer = 0;
                if (hunger > 0) {
                    hunger--;
                } else if (hunger == 0) {
                    health--;
                }
            } else if (hunger > 100) {
                hunger = 100;
            }
        }
        player.setHealth(health);
        player.setHunger(hunger);
    }

    public void render(Graphics g, long time, int days) {
        dTimer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        this.time = time / 1000;
        this.days = days;
        // Background of GUI
        for (int i = 0; i < handler.getWidth(); i += 32) {
            g.drawImage(Assets.woodUI, i, handler.getHeight() - 32, null);
        }

        // Health
        g.setColor(Color.red);
        g.fillRect(6, handler.getHeight() - 26, health * 2, 20);
        g.setColor(Color.black);
        g.drawRect(6, handler.getHeight() - 26, 200, 20);

        // Hunger
        g.setColor(Color.green);
        g.fillRect(handler.getWidth() - 206 + ((100 - hunger) * 2), handler.getHeight() - 26, 200 - ((100 - hunger) * 2), 20);
        g.setColor(Color.black);
        g.drawRect(handler.getWidth() - 206, handler.getHeight() - 26, 200, 20);

        // Font
	    g.setColor(Color.black);
	    g.setFont(hFont);
	    if (dTimer > speed) {
	        timer = 0;
	        time /= 1000;
            int w = g.getFontMetrics().stringWidth(health + " / 100");
            int wh = g.getFontMetrics().stringWidth(hunger + " / 100");
            int wt = g.getFontMetrics().stringWidth("Days: " + days + " Time: " + time);
            g.drawString(health + " / 100", 6 + (100 - w / 2), handler.getHeight() - 10);
            g.drawString(hunger + " / 100", handler.getWidth() - 6 - (100 + wh / 2), handler.getHeight() - 10);
            g.drawString("Day: " + days + " Time: " + time, 0, 15);
            g.drawString("FPS: " + fps, 0, 30);
        }
    }
}