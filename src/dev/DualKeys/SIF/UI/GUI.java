package dev.DualKeys.SIF.UI;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.entities.creatures.Player;
import dev.DualKeys.SIF.graphics.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GUI {

    private static final int MAX_HEALTH = 100;
    private static final int MAX_HUNGER = 100;
    
    private Player player;
    private Handler handler;
    
    private Font hFont;
    
    private int speed, hSpeed;
    private int health, hunger;
    private long lastTime, timer, hTimer;
    
    public GUI(Handler handler, Player player) {
        this.player = player;
        this.handler = handler;
        hFont = new Font("Helvetica", Font.BOLD, 16);
        speed = 1000;
        hSpeed = 5000;
        timer = 0;
        hTimer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public void update() {
        health = player.getHealth();
        hunger = player.getHunger();
        
        timer += System.currentTimeMillis() - lastTime;
        hTimer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            timer = 0;
            if (health < MAX_HEALTH) {
                player.setHealth(health + 1);
            }
        }
        
        if (hTimer > hSpeed) {
            hTimer = 0;
            if (hunger > 0) {
                player.setHunger(hunger - 1);
            }
        }
    }
    
    public void render(Graphics g) {
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
        int w = g.getFontMetrics().stringWidth(health + " / 100");
        int wh = g.getFontMetrics().stringWidth(hunger + " / 100");
	g.drawString(health + " / 100", 6 + (100 - w / 2), handler.getHeight() - 10);
	g.drawString(hunger + " / 100", handler.getWidth() - 6 - (100 + wh / 2), handler.getHeight() - 10);
    }
    
}