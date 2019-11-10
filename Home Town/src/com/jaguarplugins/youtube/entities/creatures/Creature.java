package com.jaguarplugins.youtube.entities.creatures;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.entities.Entity;
import com.jaguarplugins.youtube.tiles.Tile;

public abstract class Creature extends Entity {

	
	public static final float DEFAULT_SPEED = 3.0F;
	public static final int DEFAULT_CREATURE_WIDTH = 100;
	public static final int DEFAULT_CREATURE_HEIGHT = 138;
	
	
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
//		collisions with entities
		if(!checkEntityCollisions(xMove, 0F)) {
			moveX();
		}
		if(!checkEntityCollisions(0F, yMove)) {
			moveY();
		}
	}
	
	public void moveX() {
		if(xMove > 0) {//move right
//			collisions with blocks
			
//			DO NOT NEED else STATEMENTS. Put them in just to be sure
//			X coord of time we are moving into
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
//				fixes glitch where you are not flush with stone
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
			
		} else if(xMove < 0) {//move left
			
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) {//up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		}else if(yMove > 0) {//down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
//	Getters and Setters
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

}
