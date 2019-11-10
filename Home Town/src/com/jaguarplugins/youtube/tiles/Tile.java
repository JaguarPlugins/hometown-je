package com.jaguarplugins.youtube.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
//	Static Stuff:
	
	public static Tile[] tiles = new Tile[265];
	public static Tile grassTile = new GrassTile(0);
	public static Tile pathTile = new PathTile(1);
	public static Tile brickTile = new BrickTile(2);
	public static Tile waterTile = new WaterTile(3);
	public static Tile lightWaterTile = new LightWater(4);
	public static Tile flowerTile = new FlowerTile(5);
	public static Tile sandTile = new SandTile(6);
	public static Tile bridgeTile = new BridgeTile(7);
	
//	Class:
	public static final int TILEWIDTH = 100, TILEHEIGHT = 100;

	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
//		this is the default, will be overriden by grassTile
		return false;
	}
	
	public int getId () {
		return id;
	}
	
}
