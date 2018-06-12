package dev.DualKeys.SIF.states;

import dev.DualKeys.SIF.Handler;
import java.awt.Graphics;

public abstract class State {
    
    protected Handler handler;
    
    public State(Handler handler) {
        this.handler = handler;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);	
}
