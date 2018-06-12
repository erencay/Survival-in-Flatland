package dev.DualKeys.SIF;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.DualKeys.SIF.graphics.Assets;
import dev.DualKeys.SIF.graphics.GameCamera;
import dev.DualKeys.SIF.graphics.SpriteSheet;
import dev.DualKeys.SIF.input.KeyManager;
import dev.DualKeys.SIF.states.GameState;
import dev.DualKeys.SIF.states.Menu;
import dev.DualKeys.SIF.states.State;
import dev.DualKeys.SIF.states.StateManager;

public class Game implements Runnable {

    private Launcher display;
    public String title;
    public int width, height;

    public Thread thread;
    public Boolean running;

    public BufferStrategy bs;
    public Graphics g;

    public BufferedImage sheet;
    public SpriteSheet sSheet;

    public KeyManager keyManager;

    public static State gameState;
    public static State menuState;
    
    private static GameCamera gameCamera;

    private Handler handler;
    
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        this.start();
    }

    public void init() {
        display = new Launcher(title, width, height);
        display.getFrame().addKeyListener(keyManager);

        gameState = new GameState(handler);
        menuState = new Menu(handler);
        StateManager.setState(menuState);

        Assets.init();
    }

    public void update() {
        keyManager.update();
        StateManager.getState().update();
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        //
        StateManager.getState().render(g);
        //
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println(ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
}