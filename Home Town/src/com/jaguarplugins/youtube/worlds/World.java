package com.jaguarplugins.youtube.worlds;

import java.awt.Graphics;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.battle.bosses.Boss;
import com.jaguarplugins.youtube.entities.EntityManager;
import com.jaguarplugins.youtube.entities.creatures.Player;
import com.jaguarplugins.youtube.entities.statics.Enemy;
import com.jaguarplugins.youtube.entities.statics.Tree;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.items.ItemManager;
import com.jaguarplugins.youtube.tiles.Tile;
import com.jaguarplugins.youtube.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
//	Entities
	private EntityManager entityManager;
//	Items
	private ItemManager itemManager;
	
//	Bosses
	private Boss barry, cheif;
	private String[][] barryChat = {
			{"Hey", "I'm Barry"},
			{"I", "don't", "like", "you"},
			{"DIE"}
	}, cheifChat = {
			{"YOU STOLE THE SACRED...", "banana!", "You wil regret this", "Villagers!", "Throw the other, less important, bananas!"},
			{"Get out of our village"},
			{"As defender of the people of BananaVille,", "I order you to leave"},
			{"BANANA!"}
	};
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 200, 200));
		itemManager = new ItemManager(handler);
		
		loadWorld(path);
		
//		randomTreeRender();
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
//		Enemies
		cheif = new Boss(handler, Assets.cheif, cheifChat, 40, 2);
		barry = new Boss(handler, Assets.barry, barryChat, 30, 1);
		entityManager.addEntity(new Enemy(handler, 300, 300, barry));
		entityManager.addEntity(new Enemy(handler, 500, 600, cheif));
		
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.max(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++) {
			for(int x = xStart;x < xEnd;x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
//		Items
		itemManager.render(g);
//		Entity
		entityManager.render(g);
		
	}
	
	private void randomTreeRender() {
		for(int i = 0; i < 50;) {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
//			TODO add tree width and height to x and y in next line!!!
//			TODO not numbers: add getWidth function for tree
			if(getTile(x, y).equals(Tile.grassTile)) {
				entityManager.addEntity(new Tree(handler, x * 100, y * 100));
				i++;
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.bridgeTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
//			Default dirt tile if the index is too high and isn't a valid tile
			return Tile.brickTile;
		}
		return t;
	}
	
	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		
		for(int y = 0;y < height;y++) {
			for(int x = 0;x < width;x++) {
//				add 4 because it's from the 4th index in out list tokens
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
}
