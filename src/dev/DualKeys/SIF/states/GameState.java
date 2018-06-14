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

    public GameState(Handler handler, GameTimeManager gameTimeManager) {
        super(handler);
        this.gameTimeManager = gameTimeManager;
        Assets.init();

        world = new World(handler, getClass().getResourceAsStream("/Worlds/world.world"), true);
        handler.setWorld(world);
        player = new Player(handler, 32, 32);
        gui = new GUI(handler, player, gameTimeManager);
        zombies = new Zombie[11];
    }

    @Override
    public void update() {
        world.update();
        player.update();
        gui.update();

        GameTimeInfo currentTime = gameTimeManager.getGameTimeInfo();

        for (int i = 0; i < zombies.length - 1; i++) {
            if (zombies[i] != null) {
                zombies[i].update();
            }
        }
        if (zombies[10] != null) {
            zombies[0] = zombies[10]; // zom6 zom1 zom2 zom3 zom4 zom5 null
            zombies[10] = null;
        }
        if (currentTime.isNightTime()) {
            if (lastZombieSpawn == null || currentTime.elapsedMinutes() - lastZombieSpawn.elapsedMinutes() >= 60) {
                for (int i = 0; i < zombies.length; i++) {
                    if (zombies[i] == null) {
                        zombies[i] = new Zombie(handler,
                                player,
                                (float) Math.floor(Math.random() * (handler.getWorld().getWidth() * Tile.WIDTH)),
                                (float) Math.floor(Math.random() * (handler.getWorld().getHeight() * Tile.HEIGHT)));
                        lastZombieSpawn = currentTime;
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