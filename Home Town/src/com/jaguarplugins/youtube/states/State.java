package com.jaguarplugins.youtube.states;

import java.awt.Graphics;

import com.jaguarplugins.youtube.Handler;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		
		currentState = state;
		
	}
	
	public static State getState() {
		
		return currentState;
		
	}

	//class
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public void initialise() {
		init();
		State.setState(this);
	}
	
	protected abstract void init();

	

}
