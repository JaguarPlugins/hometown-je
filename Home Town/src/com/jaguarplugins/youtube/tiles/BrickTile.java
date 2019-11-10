package com.jaguarplugins.youtube.tiles;

import com.jaguarplugins.youtube.gfx.Assets;

public class BrickTile extends Tile{

	public BrickTile(int id) {
		super(Assets.brick, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
	
}