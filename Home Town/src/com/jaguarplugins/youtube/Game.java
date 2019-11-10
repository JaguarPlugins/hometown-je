package com.jaguarplugins.youtube;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.jaguarplugins.youtube.display.Display;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.gfx.GameCamera;
import com.jaguarplugins.youtube.input.KeyManager;
import com.jaguarplugins.youtube.input.MouseManager;
import com.jaguarplugins.youtube.states.BattleState;
import com.jaguarplugins.youtube.states.GameState;
import com.jaguarplugins.youtube.states.MenuState;
//import com.jaguarplugins.youtube.states.MenuState;
import com.jaguarplugins.youtube.states.State;

public class Game implements Runnable {
	
	private Display display;
	public String TITLE;
	private int WIDTH;
	private int HEIGHT;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
//	private BufferedImage testImage;
//	Sprite size: 100 x 138
//	private SpriteSheet sheet;
	
//	States
	public State gameState;
	public State menuState;
	public State battleState;
	
//	Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
//	Camera
	private GameCamera gameCamera;
	
//	Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {

		this.WIDTH = width;
		this.HEIGHT = height;
		this.TITLE = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
	}

	private void init() {
//		Gets the game  ready
		
		display = new Display(TITLE, WIDTH, HEIGHT);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0,0);
		
//		state on load
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		battleState = new BattleState(handler);
		gameState.initialise();
		
	}
	
	private void tick() {
//		Updates all variables and objects
		
		keyManager.tick();
		
		if(State.getState() != null) {
			State.getState().tick();
		}
		
	}
	
	private void render() {
//		Renders (draws) everything to screen
		
//		A buffer is like a screen behind the screen that you draw on
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
//		this object allows you to draw
		g = bs.getDrawGraphics();
//		clear screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		
//		moves the buffer onto the screen
		bs.show();
		g.dispose();
		
	}
	
	public void run() {
//		this is necessary for Runnable to work
//		Runs the thread (separate program)
		
		init();
		
		int fps = 60;
//		1,000,000,000 nano seconds is one second
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while(running) {
			
//			keeps the system running at 60 fps
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta > 1) {
				
				tick();
				render();
				delta --;
				
			}
			
			
//			Displays fps in console
			if(timer >= 1_000_000_000) {
				
//				System.out.println("Fps: " + ticks);
				timer = 0;
				
			}
			
		}
		
		stop();
		
	}
	 
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public synchronized void start() {
//		will run this class on a new thread
		
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public State getGameState() {
		return gameState;
	}

	public State getBattleState() {
		return battleState;
	}

}
