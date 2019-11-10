package com.jaguarplugins.youtube.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.gfx.Text;
import com.jaguarplugins.youtube.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		addItem(Item.stickItem.createNew(5));
	}
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
//			Swaps the value of active
			active = !active;
		}
		if(!active) {
			return;
		}
		
	}
	
	public void render(Graphics g) {
		if(!active) {
			return;
		}
		
		g.drawImage(Assets.inventoryScreen, ((handler.getWidth() / 2) - (Assets.inventoryScreen.getWidth() / 2)), 
				((handler.getHeight() / 2) - (Assets.inventoryScreen.getHeight() / 2)), null);
		Text.drawString(g, "stick", 0, 20, false, Color.WHITE, Assets.font28);
		
	}
	
//	Inventory:
	
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
//	GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
