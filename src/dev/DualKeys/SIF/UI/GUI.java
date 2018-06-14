package dev.DualKeys.SIF.UI;

import dev.DualKeys.SIF.GameTimeInfo;
import dev.DualKeys.SIF.GameTimeManager;
import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.entities.creatures.Player;
import dev.DualKeys.SIF.graphics.Assets;

import java.awt.*;

public class GUI {

    private static final int MAX_HEALTH = 100;
    private static final int MAX_HUNGER = 100;
    
    private Player player;
    private Handler handler;
    private GameTimeManager gameTimeManager;

    private Font hFont;
    
    private int speed, hSpeed, sSpeed;
    private int health, hunger, thirst, stamina;
    private GameTimeInfo time;
    private int days;
    private long lastTime, timer, hTimer, dTimer, sTimer;
    
    public GUI(Handler handler, Player player, GameTimeManager gameTimeManager) {
        this.gameTimeManager = gameTimeManager;
        this.player = player;
        this.handler = handler;
        hFont = new Font("Helvetica", Font.BOLD, 16);
        speed = 1000;
        sSpeed = 500;
        hSpeed = 5000;
        timer = 0;
        hTimer = 0;
        dTimer = 0;
        sTimer = 0;
        days = gameTimeManager.getGameTimeInfo().Days;
        time = gameTimeManager.getGameTimeInfo();
        lastTime = System.currentTimeMillis();
    }
    
    public void update() {
        health = player.getHealth();
        hunger = player.getHunger();
        stamina = player.getStamina();
        thirst = player.getThirst();

        timer += System.currentTimeMillis() - lastTime;
        hTimer += System.currentTimeMillis() - lastTime;
        sTimer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (handler.getState() == handler.getGameState()) {
            if (timer > speed) {
                timer = 0;
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
                if (thirst > 0) {
                    thirst--;
                } else if (thirst == 0) {
                    thirst--;
                }
            } else if (hunger > 100) {
                hunger = 100;
            } else if (thirst > 100) {
                thirst = 100;
            }
        }
        player.setHealth(health);
        player.setHunger(hunger);
        player.setThirst(thirst);
        player.setStamina(stamina);
    }

    public void render(Graphics g) {
        dTimer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        // Background of GUI
        for (int i = 0; i < handler.getWidth(); i += 60) {
            g.drawImage(Assets.woodUI, i, handler.getHeight() - 64, null);
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
        g.drawRect(handler.getWidth() - 206, handler.getHeight() - 26, 200, 20);g.setColor(Color.green);

        // Thirst
        g.setColor(Color.decode("0x00ffff"));
        g.fillRect(handler.getWidth() - 206 + ((100 - hunger) * 2), handler.getHeight() - (26 * 2), 200 - ((100 - thirst) * 2), 20);
        g.setColor(Color.black);
        g.drawRect(handler.getWidth() - 206, handler.getHeight() - (26 * 2), 200, 20);

        // Stamina
        g.setColor(Color.blue);
        g.fillRect(6, handler.getHeight() - (26 * 2), stamina * 4, 20);
        g.setColor(Color.black);
        g.drawRect(6, handler.getHeight() - (26 * 2), 200, 20);

        // Font
	    g.setColor(Color.black);
	    g.setFont(hFont);
	    if (dTimer > speed) {
	        timer = 0;
            int w = g.getFontMetrics().stringWidth(health + " / 100");
            int wh = g.getFontMetrics().stringWidth(hunger + " / 100");
            g.drawString(health + " / 100", 6 + (100 - w / 2), handler.getHeight() - 10);
            g.drawString(hunger + " / 100", handler.getWidth() - 6 - (100 + wh / 2), handler.getHeight() - 10);
            g.drawString("Day: " + days + " Time: " + time.Hour + ":" + time.Minute, 0, 15);
        }
    }
}