package com.jaguarplugins.youtube.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.entities.Entity;
import com.jaguarplugins.youtube.gfx.Animation;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.inventory.Inventory;

public class Player extends Creature{
	
	private Animation animRight, animLeft, animUp, animDown;
	
//	Attack Timer
	private long lastAttackTimer, attackCoolDown = 800, attackTimer = attackCoolDown;
	
	private Inventory inventory;
	
	public Rectangle ar;
	public boolean flash = false;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 20;
		bounds.y = 80;
		bounds.width = 60;
		bounds.height =  40;
		
//		Animations
		animRight = new Animation(400, Assets.player_right);
		animLeft = new Animation(400, Assets.player_left);
		animUp = new Animation(400, Assets.player_up);
		animDown = new Animation(400, Assets.player_down);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
	
//		Animations
		animRight.tick();
		animLeft.tick();
		animUp.tick();
		animDown.tick();
//		Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
//		Attack
		checkAttacks();
		inventory.tick();
		
	}
	
	private void checkAttacks() {
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCoolDown) {
			return;
		}
		
//		collision bounds (of player)
		Rectangle cb = getCollisionBounds(0, 0);
//		attack rectangle
		Rectangle ar = new Rectangle();
		int arSize = 20;
//		(range)
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}
		
		attackTimer = 0;
		flash = true;
		System.out.println("Break");
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
//				TODO change depending on equipped MELE item
				e.hurt(1);
				return;
			}
		}
	}
	
	@Override
	public void die() {
		System.out.println("You Lose");
//TODO	State.setState(handler.getGame().deathState);
		
	}
		
	private void getInput() {
		
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
	
	}

	@Override
	public void render(Graphics g) {
		
//		x and y are floats
//		width and height are from creature statics
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		
////		if(flash) {
////			g.setColor(Color.yellow);
////			g.drawOval((int) (ar.x - handler.getGameCamera().getxOffset()), (int) (ar.y - handler.getGameCamera().getyOffset()), ar.width, ar.height);
////		}
		
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
		
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove > 0) {
			return animRight.getCurrentFrame();
		} else if(xMove < 0){
			return animLeft.getCurrentFrame();
		} else if(yMove > 0) {
			return animDown.getCurrentFrame();
		} else if(yMove < 0) {
			return animUp.getCurrentFrame();
		} else {
			return Assets.player;
		}
	}

	public Inventory getInventory() {
		return inventory;
	}
}
