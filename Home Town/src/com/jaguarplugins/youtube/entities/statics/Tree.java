package com.jaguarplugins.youtube.entities.statics;

import java.awt.Graphics;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.items.Item;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, 225, 225);
		
		bounds.x = 86;
		bounds.y = 155;
		bounds.width = 46;
		bounds.height = 68;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
		
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.stickItem.createNew((int) x, (int) y));
	}

}
