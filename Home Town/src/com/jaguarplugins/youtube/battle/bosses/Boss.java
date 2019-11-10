package com.jaguarplugins.youtube.battle.bosses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.gfx.Animation;

public class Boss {
	
	private Animation anim;
	
	private Handler handler;
	private String[][] chat;
	private int health, damage, totalHealth;
	private BufferedImage[] sprites;
	
	private int xPos, yPos = 30, width = 200, height = 276;
	
	public Boss(Handler handler, BufferedImage[] sprites, String[][] chat, int health, int damage) {
		this.handler = handler;
		this.sprites = sprites;
		this.chat = chat;
		this.health = health;
		this.totalHealth = health;
		this.damage = damage;
		
		anim = new Animation(800, sprites);
		xPos = ((handler.getWidth() / 4) * 3) - (anim.getCurrentFrame().getWidth() * 4 / 2);
	}
	
	public void tick() {
		if(health <= 0) {
			die();
		}
		anim.tick();
	}
	
	public void render(Graphics g) {
		g.drawImage(anim.getCurrentFrame(), xPos,
				yPos, width, height, null);
		if(health > 0) {
			g.setColor(Color.black);
			g.drawRect(xPos - 1, 11, 201, 16);
			g.setColor(Color.blue);
			g.fillRect(xPos, 12, 200 * health / totalHealth, 15);
		}
	}
	
	private void die() {
		handler.getGame().gameState.initialise();
	}

//	GETTERS SETTERS
	public int getHealth() {
		return health;
	}

	public void hurt(int damage) {
		this.health -= damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String[][] getChat() {
		return chat;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
