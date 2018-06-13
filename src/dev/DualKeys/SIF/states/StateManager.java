package dev.DualKeys.SIF.states;

import dev.DualKeys.SIF.Game;
import dev.DualKeys.SIF.sound.AudioMap;
import dev.DualKeys.SIF.sound.SoundThread;

public class StateManager {

    public static State currentState = null;

    private static SoundThread soundThread;

    public static State getState() {
	return currentState;
    }

    public static void setState(State state) {
        if (state == Game.getHandler().getGameState()) {
            new AudioMap();
            soundThread = new SoundThread();
            soundThread.run();
        }
        currentState = state;
    }
	
}
