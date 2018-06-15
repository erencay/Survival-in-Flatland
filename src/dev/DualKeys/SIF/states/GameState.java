package dev.DualKeys.SIF.states;

import dev.DualKeys.SIF.GameTimeInfo;
import dev.DualKeys.SIF.GameTimeManager;
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
    private GameTimeManager gameTimeManager;

    GameTimeInfo timeInfo;
    GameTimeInfo lastZombieSpawn;

    private int spawnX;
    private int spawnY;

    public GameState(Handler handler, GameTimeManager gameTimeManager, Boolean isRandom) {
        super(handler);
        this.gameTimeManager = gameTimeManager;
        if (!isRandom) {
            world = new World(handler, getClass().getResourceAsStream("/Worlds/default.world"), false);
        } else {
            world = new World(handler, getClass().getResourceAsStream("/Worlds/random.world"), true);
        }
        handler.setWorld(world);
        Assets.init();

        spawnX = (int)Math.floor(Math.random() * (handler.getWorld().getWidth() * Tile.WIDTH));
        spawnY = (int)Math.floor(Math.random() * (handler.getWorld().getHeight() * Tile.HEIGHT));

        player = new Player(handler, spawnX, spawnY);
        gui = new GUI(handler, player, gameTimeManager);
        zombies = new Zombie[51];
    }

    @Override
    public void update() {
        world.update();
        player.update();
        gui.update();

        if (handler.getKeyManager().quit) {
            StateManager.setState(handler.getMenuState());
        }

        GameTimeInfo currentTime = gameTimeManager.getGameTimeInfo();

        for (int i = 0; i < zombies.length - 1; i++) {
            if (zombies[i] != null) {
                zombies[i].update();
            }
        }
        if (zombies[50] != null) {
            zombies[0] = zombies[50];
            zombies[50] = null;
        }
        if (currentTime.isNightTime()) {
            if (lastZombieSpawn == null || currentTime.elapsedMinutes() - lastZombieSpawn.elapsedMinutes() >= 60) {
                for (int i = 0; i < zombies.length; i++) {
                    if (zombies[i] == null) {
                        for(int j = 0; j < 5; j++) {
                            zombies[i] = new Zombie(handler,
                                    player,
                                    (float) Math.floor(Math.random() * (handler.getWorld().getWidth() * Tile.WIDTH)),
                                    (float) Math.floor(Math.random() * (handler.getWorld().getHeight() * Tile.HEIGHT)));
                            lastZombieSpawn = currentTime;
                        }
                        break;
                    }
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
}