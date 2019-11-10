package com.jaguarplugins.youtube.states;

import java.awt.Graphics;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.worlds.World;

public class GameState extends State {

	private World world;
	
	public GameState(Handler handler) {
		
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

}