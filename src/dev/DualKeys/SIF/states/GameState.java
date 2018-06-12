package dev.DualKeys.SIF.states;

import dev.DualKeys.SIF.worlds.World;
import java.awt.Graphics;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.UI.GUI;
import dev.DualKeys.SIF.entities.creatures.Player;
import dev.DualKeys.SIF.graphics.Assets;

public class GameState extends State {

    private Player player;
    private GUI gui;
    private World world;
	
    public GameState(Handler handler) {
	super(handler);
        Assets.init();
        gui = new GUI(handler, player);
	player = new Player(handler, 32, 32);
        world = new World(handler, "res/Worlds/world1.world");
    }
	
    @Override
    public void update() {
        world.update();
	player.update();
        gui.update();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
	player.render(g);
        gui.render(g);
    }

}
