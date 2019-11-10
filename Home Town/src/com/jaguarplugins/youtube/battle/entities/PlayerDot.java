package com.jaguarplugins.youtube.battle.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import com.jaguarplugins.youtube.Handler;

public class PlayerDot {

	private Handler handler;
	
	private int totalhealth, health, x, y, width = 20, height = 20, speed = 8;
	private int xMove, yMove;
	
	private Rectangle bounds;
	
	public PlayerDot(Handler handler, int health, int x, int y) {
		
		this.handler = handler;
		this.health = health;
		this.x = x;
		this.y = y;
		
		totalhealth = health;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);
	}
	
	public void tick(Rectangle zone) {
		
		move(zone);
		bounds.setLocation(x, y);
		if(health <= 0) {
			die();
		}
	}
	
	private void die() {
		JOptionPane.showMessageDialog(null, "ERROR 404, your bad");
		System.exit(0);
	}
	
	private void move(Rectangle zone) {
		
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().w) {
			yMove = -speed;
		}
		if(handler.getKeyManager().s) {
			yMove = speed;
		}
		if(handler.getKeyManager().a) {
			xMove = -speed;
		}
		if(handler.getKeyManager().d) {
			xMove = speed;
		}
		
		Rectangle newPos = new Rectangle(x + xMove, y + yMove, width, height);
		Rectangle newZone = new Rectangle((int)zone.getX() + width, (int)zone.getY() + height, (int)zone.getWidth() - width * 2, 
				(int)zone.getHeight() - height * 2);
		
		if(newZone.intersects(newPos)) {
			x += xMove;
			y += yMove;
		}
	}

	public void newBoss() {
		health = totalhealth;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void hurt(int dammage) {
		health -= dammage;
	}
	
	public void heal(int healing) {
		health += healing;
	}
	
	public void resetHealth() {
		this.health = totalhealth;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getTotalhealth() {
		return totalhealth;
	}
	
}
