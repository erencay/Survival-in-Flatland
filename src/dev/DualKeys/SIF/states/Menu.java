package dev.DualKeys.SIF.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.DualKeys.SIF.Game;
import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.graphics.Assets;

public class Menu extends State {

    public static float loading = 0;

    private Handler handler;
    
    public Menu(Handler handler) {
        this.handler = handler;
    }
    
    public static float loading() {
	loading += 0.5;
	return loading;
    }
    
    @Override
    public void update() {
	if (loading >= 200) {
            StateManager.setState(Game.gameState);
	}
    }

    @Override
    public void render(Graphics g) {
	g.setColor(Color.black);
	g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
	g.setColor(Color.green);
	g.fillRect((handler.getWidth() / 2) - 100, (handler.getHeight() / 2) - 10, (int)loading(), 20);
	g.setColor(Color.white);
	g.drawRect((handler.getWidth() / 2) - 100, (handler.getHeight() / 2) - 10, 200, 20);
	g.drawImage(Assets.title, (handler.getWidth() / 2) - 256, 200, 512, 64, null);
    }

}
