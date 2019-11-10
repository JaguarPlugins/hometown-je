package com.jaguarplugins.youtube.tiles;

import com.jaguarplugins.youtube.gfx.Assets;

public class LightWater extends Tile{

	public LightWater(int id) {
		super(Assets.lightWater, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
	
}