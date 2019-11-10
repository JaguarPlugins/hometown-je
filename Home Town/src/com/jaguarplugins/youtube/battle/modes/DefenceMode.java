package com.jaguarplugins.youtube.battle.modes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.battle.bosses.Boss;
import com.jaguarplugins.youtube.states.BattleState;

public class DefenceMode extends Mode{

//	Generation Settings
	private static int ticks = 100;
	private int speed = 8;
	
	private long time, startTime;
	
	private ArrayList<Rectangle> dots;
	private int dotWidth = 15, dotHeight = 15;
	
	public DefenceMode(Handler handler, Rectangle zone, Boss boss) {
		super(handler, zone, boss);
		dots = new ArrayList<Rectangle>();
	}
	
	@Override
	public void init() {
		startTime = System.currentTimeMillis();
		dots.clear();
	}

	@Override
	public  void tick() {
		
		ticks++;
		if(ticks >= 10) {
			generateDots();
			ticks = 0; 
		}
		deleteDots();
		
		for(Rectangle r : dots) {
			r.setLocation((int)r.getX() - speed, (int)r.getY());
		}
		
		BattleState.getPlayerDot().tick(zone);
		checkPlayerCollisions();
		
		time = (System.currentTimeMillis() - startTime) / 1000;
		
		if(time > 30) {
			Mode.nextMode();
		}
		
	}

	@Override
	public void render(Graphics g) {
		for(Rectangle r : dots) {
			g.setColor(Color.black);
			g.fillOval((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		}
		
		BattleState.getPlayerDot().render(g);
		
		g.setColor(Color.black);
		g.drawString("" + time, 0, 20);
	}
	
	private void checkPlayerCollisions(){
		Iterator<Rectangle> it = dots.iterator();
		while(it.hasNext()) {
			Rectangle r = it.next();
			if(r.intersects(BattleState.getPlayerDot().getBounds())) {
				it.remove();
				BattleState.getPlayerDot().hurt(boss.getDamage());
			}
		}
	}
	
	private void generateDots() {
		int yPos = (int) (Math.random() * (handler.getHeight() / 2 - 20 - dotHeight) + (handler.getHeight() / 2 + 10));
		dots.add(new Rectangle(handler.getWidth(), yPos, dotWidth, dotHeight));
	}
	
	private synchronized void deleteDots() {
		Iterator<Rectangle> it = dots.iterator();
		while(it.hasNext()) {
			Rectangle r = it.next();
			if(r.getX() < (0 - dotWidth) - dotWidth) {
				it.remove();
			}
		}
	}

	public int getDotWidth() {
		return dotWidth;
	}

	public int getDotHeight() {
		return dotHeight;
	}

	@Override
	public void newBoss() {
		
	}
}
