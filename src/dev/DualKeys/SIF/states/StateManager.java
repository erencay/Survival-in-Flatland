package dev.DualKeys.SIF.states;

public class StateManager {

    public static State currentState = null;

    public static State getState() {
	return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }
	
}
