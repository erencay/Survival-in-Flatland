package dev.DualKeys.SIF.states;

import dev.DualKeys.SIF.Game;
import dev.DualKeys.SIF.sound.AudioMap;
import dev.DualKeys.SIF.sound.SoundThread;

public class StateManager {

    public static State currentState = null;
    public static boolean menu, game;

    private static SoundThread soundThread;

    public StateManager() {
        new AudioMap();
        soundThread = new SoundThread();
    }

    public static State getState() {
	    return currentState;
    }

    public static void setState(State state) {
        if (state == Game.getHandler().getGameState()) {
            soundThread.run();
            game = true;
            menu = false;
        } else if (state == Game.getHandler().getMenuState()) {
            soundThread.pause();
            game = false;
            menu = true;
        }
        currentState = state;
    }
	
}
