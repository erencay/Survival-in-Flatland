package dev.DualKeys.SIF.states;

import dev.DualKeys.SIF.Handler;
import dev.DualKeys.SIF.graphics.Assets;

import java.awt.*;

public class Menu extends State {


    private Handler handler;

    private int choice = 0;
    private final String[] options = {
            "Start",
            "Settings",
            "Quit"
    };
    private Font font;

    public Menu(Handler handler) {
        super(handler);
        this.handler = handler;
        font = new Font("Arial", Font.BOLD, 50);
    }

    @Override
    public void update() {
        keyPressed();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.setFont(font);
        for(int i = 0; i < options.length; i++) {
            int w = g.getFontMetrics().stringWidth(options[i]);
            if(i == choice) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.gray);
            }
            g.drawString(options[i], handler.getWidth() / 2 - w / 2, 275 + i * 50);
        }
        g.drawImage(Assets.title, handler.getWidth() / 2 - 256, 64, 512, 64, null);
    }

    private void select() {
        switch (choice) {
            case 0:
                StateManager.setState(handler.getGameState());
                break;
            case 1:
                StateManager.setState(handler.getGameState());
                break;
            case 2:
                System.exit(0);
            default:
                break;
        }
    }

    public void keyPressed() {
        if(handler.getKeyManager().select){
            select();
        }
        if(handler.getKeyManager().menuUp) {
            choice--;
            if(choice == -1) {
                choice = options.length - 1;
            }
        }
        if(handler.getKeyManager().menuDown) {
            choice++;
            if(choice == options.length) {
                choice = 0;
            }
        }
    }

}




