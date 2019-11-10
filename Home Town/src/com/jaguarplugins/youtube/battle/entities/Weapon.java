package com.jaguarplugins.youtube.battle.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.gfx.Text;
import com.jaguarplugins.youtube.states.BattleState;
import com.jaguarplugins.youtube.states.State;

public class Weapon {

	private Handler handler;
	private String name, description;
	private BufferedImage sprite;
	private int damage, quantity, rarity, lastingDamage, healing;
	private boolean infinite, good;
	
//	Useful positions
	private int yCentre = (int) BattleState.getZone().getY() + (int) (BattleState.getZone().getHeight() / 2);
	private int xCentre = (int) BattleState.getZone().getWidth() / 2;
	private int quart = (int) BattleState.getZone().getWidth() / 4;
	
//	OVERLOADED: Special weapon (with lasting damage / infinite)
	public Weapon(Handler handler, String name, String description, BufferedImage sprite, int dammage, int quantity, int rarity, int lastingDamage, boolean infinite) {
		this.handler = handler;
		this.name = name;
		this.description = description;
		this.sprite = sprite;
		this.damage = dammage;
		this.quantity = quantity;
		this.rarity = rarity;
		this.lastingDamage = lastingDamage;
		this.infinite = infinite;
		this.good = false;
	}
	
//	OVERLOADED: Standard weapon
	public Weapon(Handler handler, String name, String description, BufferedImage sprite, int dammage, int quantity, int rarity) {
		this.handler = handler;
		this.name = name;
		this.description = description;
		this.sprite = sprite;
		this.damage = dammage;
		this.quantity = quantity;
		this.rarity = rarity;
		this.lastingDamage = 0;
		this.infinite = false;
		this.good = false;
	}
	
//	OVERLOADED: Good Weapon (e.g. healing)
	public Weapon(boolean good, Handler handler, String name, String description, BufferedImage sprite, int healing, int quantity, int rarity) {
		this.handler = handler;
		this.name = name;
		this.description = description;
		this.sprite = sprite;
		this.damage = 0;
		this.healing = healing;
		this.quantity = quantity;
		this.rarity = rarity;
		this.lastingDamage = 0;
		this.infinite = false;
		this.good = true;
	}

	public void tick() {
		if(State.getState().equals(handler.getGame().battleState)) {
			battleTick();
		}
	}
	
	protected void battleTick() {
		
	}
	
	public void render(Graphics g) {
		if(State.getState().equals(handler.getGame().battleState)) {
			battleRender(g);
		}
	}
	
	protected void battleRender(Graphics g) {
//		Name
		Text.drawString(g, name, xCentre, yCentre - 80, true, Color.black, Assets.font100);
//		Description
		Text.drawString(g, description, xCentre, yCentre, true, Color.blue, Assets.font28);
//		Damage
		if(good == true) {
			Text.drawString(g, "Heals:", xCentre, yCentre + 50, true, Color.darkGray, Assets.font28);
			Text.drawString(g, "" + healing, xCentre, yCentre + 80, true, Color.darkGray, Assets.font28);
		} else {
			Text.drawString(g, "Dammage:", xCentre, yCentre + 50, true, Color.darkGray, Assets.font28);
			if(lastingDamage > 0) {
				Text.drawString(g, "" + damage + " (" + lastingDamage + ")", xCentre, yCentre + 80, true, Color.darkGray, Assets.font28);
			} else {
				Text.drawString(g, "" + damage, xCentre, yCentre + 80, true, Color.darkGray, Assets.font28);
			}
		}
//		Durability NOTE: Written as quantity
		Text.drawString(g, "Quantity:", xCentre - quart, yCentre + 50, true, Color.darkGray, Assets.font28);
		if(infinite == true) {	
			Text.drawString(g, "infinite", xCentre - quart, yCentre + 80, true, Color.darkGray, Assets.font28);
		} else {
			Text.drawString(g, "" + quantity, xCentre - quart, yCentre + 80, true, Color.darkGray, Assets.font28);
		}
//		Rarity
		Text.drawString(g, "Rarity:", xCentre + quart, yCentre + 50, true, Color.darkGray, Assets.font28);
		Text.drawString(g, "" + rarity, xCentre + quart, yCentre + 80, true, Color.darkGray, Assets.font28);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getLastingDamage() {
		return lastingDamage;
	}

	public void setLastingDamage(int lastingDamage) {
		this.lastingDamage = lastingDamage;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public int getDamage() {
		return damage;
	}

	public boolean isInfinite() {
		return infinite;
	}

	public int getHealing() {
		return healing;
	}
	
}
