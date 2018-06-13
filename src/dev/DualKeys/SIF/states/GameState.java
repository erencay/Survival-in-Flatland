package dev.DualKeys.SIF.states;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.UI.GUI;
import dev.DualKeys.SIF.entities.creatures.Player;
import dev.DualKeys.SIF.entities.creatures.Zombie;
import dev.DualKeys.SIF.graphics.Assets;
import dev.DualKeys.SIF.tiles.Tile;
import dev.DualKeys.SIF.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private Zombie[] zombies;
    private GUI gui;
    private World world;

    public static int night, day, days, zTime;
    public static long timer, zTimer, lastTime;

    public GameState(Handler handler) {
	    super(handler);
        Assets.init();
        world = new World(handler, getClass().getResourceAsStream("/Worlds/world1.world"));
        handler.setWorld(world);
	    player = new Player(handler, 32, 32);
        gui = new GUI(handler, player);
        zombies = new Zombie[7];

        night = 6000;
        day = 36000;
        days = 1;
        zTime = 6000;
        timer = 0;
        zTimer = 0;
        lastTime = System.currentTimeMillis();
    }
	
    @Override
    public void update() {
        world.update();
	    player.update();
        for (int i = 0; i < zombies.length - 1; i++) {
            if (zombies[i] != null) {
                zombies[i].update();
            }
        }
        if (zombies[6] != null) {
            zombies[0] = zombies[6]; // zom6 zom1 zom2 zom3 zom4 zom5 null
            zombies[6] = null;
        }
        gui.update();

        timer += System.currentTimeMillis() - lastTime;
        zTimer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer >= 144000) {
            timer = 0;
            days++;
        }
        if (timer > night && timer < day) {
            System.out.println("True1");
            if (zTimer > zTime) {
                System.out.println("True2");
                zTimer = 0;
                for (int i = 0; i < zombies.length; i++) {
                    if (zombies[i] == null) {
                        System.out.println("Zombie");
                        zombies[i] = new Zombie(handler, player, (float) Math.floor(Math.random() * (handler.getWorld().getWidth() * Tile.WIDTH)), (float) Math.floor(Math.random() * (handler.getWorld().getHeight() * Tile.HEIGHT)));
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
	    player.render(g);
        for (int i = 0; i < zombies.length - 1; i++) {
            if (zombies[i] != null) {
                zombies[i].render(g);
            }
        }
        gui.render(g);
    }

    public static int getDays() {
        return days;
    }

    public static long getTimer() {
        return timer;
    }
}
