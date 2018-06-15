package dev.DualKeys.SIF.input;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.states.Menu;
import dev.DualKeys.SIF.states.StateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    public boolean up, down, left, right, food, select, menuDown, menuUp, run, quit;
    private Handler handler;
    private boolean[] keys;

    public KeyManager(Handler handler) {
        this.handler = handler;
        keys = new boolean[256];
    }

    public void update() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        food = keys[KeyEvent.VK_F];
//        select = keys[KeyEvent.VK_ENTER];
//        menuDown = keys[KeyEvent.VK_DOWN];
//        menuUp = keys[KeyEvent.VK_UP];
        run = keys[KeyEvent.VK_SHIFT];
        quit = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if (keys[KeyEvent.VK_ENTER] && StateManager.menu) {
            Menu.select();
        } else if (keys[KeyEvent.VK_UP] && StateManager.menu) {
            Menu.choice--;
            if (Menu.choice == -1) {
                Menu.choice = Menu.options.length - 1;
            }
        } else if (keys[KeyEvent.VK_DOWN] && StateManager.menu) {
            Menu.choice++;
            if (Menu.choice == Menu.options.length) {
                Menu.choice = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
