package com.jaguarplugins.youtube.tiles;

import com.jaguarplugins.youtube.gfx.Assets;

public class WaterTile extends Tile{

	public WaterTile(int id) {
		super(Assets.water, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}