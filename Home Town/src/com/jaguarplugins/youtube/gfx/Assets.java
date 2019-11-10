package com.jaguarplugins.youtube.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
//	Sprite size: 100 x 138
	
//	THESE TWO WILL BE USED WHEN I UPGRADE TO A SPRITE SHEET
//	private static final int width = 50;
//	private static final int height = 69;
	
	private static final int width1 = 46;
	private static final int height1 = 69;
	private static final int width2 = 16;
	private static final int height2 = 16;
	
	public static Font font24, font28, font100;
	
//	THIS IS VERY BAD PRACTICE
	public static BufferedImage[] player_right, player_left, player_up, player_down;
	public static BufferedImage player;
	public static BufferedImage brick, bridge, flower, grass, lightWater, path, sand, water;
	public static BufferedImage logo;
	public static BufferedImage[] start;
	public static BufferedImage tree, stick;
	public static BufferedImage inventoryScreen;
	public static BufferedImage[] barry, cheif;
	
	public static void init() {
		
		font24 = FontLoader.loadFont("res/fonts/broadway.ttf", 24);
		font28 = FontLoader.loadFont("res/fonts/broadway.ttf", 28);
		font100 = FontLoader.loadFont("res/fonts/broadway.ttf", 100);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Tom.png"));
		SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png"));
		
//		SpriteSheet bossSheet = new SpriteSheet(ImageLoader.loadImage("/textures/bosses.png"));
		
		SpriteSheet logoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Logo.jpg"));
		SpriteSheet startSheet = new SpriteSheet(ImageLoader.loadImage("/textures/start.png"));
		SpriteSheet startPressedSheet = new SpriteSheet(ImageLoader.loadImage("/textures/startPresed.png"));
		SpriteSheet stickSheet = new SpriteSheet(ImageLoader.loadImage("/textures/stick.png"));
		
//		TODO make all menu buttons into 1 sheet
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventory.png");
		
		logo = logoSheet.crop(0, 0, 400, 300);
		
		stick = stickSheet.crop(0, 0, 225, 225);
		
		start = new BufferedImage[2];
		start[0] = startSheet.crop(0, 0, 445, 113);
		start[1]= startPressedSheet.crop(0, 0, 445, 113);
		
		barry = new BufferedImage[2];
		barry[0] = ImageLoader.loadImage("/textures/bosses/01barry.png");
		barry[1] = ImageLoader.loadImage("/textures/bosses/11barry.png");
		
		cheif = new BufferedImage[2];
		cheif[0] = ImageLoader.loadImage("/textures/bosses/02cheif.png");
		cheif[1] = ImageLoader.loadImage("/textures/bosses/12cheif.png");
		
		player_down = new BufferedImage[4];
		player_down[0] = sheet.crop(0, 0, width1, height1);
		player_down[1] = sheet.crop(width1, 0, width1, height1);
		player_down[2] = sheet.crop(width1 * 2, 0, width1, height1);
		player_down[3] = sheet.crop(width1 * 3, 0, width1, height1);
		
		player_up = new BufferedImage[4];
		player_up[0] = sheet.crop(0, height1, width1, height1);
		player_up[1] = sheet.crop(width1, height1, width1, height1);
		player_up[2] = sheet.crop(width1 * 2, height1, width1, height1);
		player_up[3] = sheet.crop(width1 * 3, height1, width1, height1);
		
		player_left = new BufferedImage[4];
		player_left[0] = sheet.crop(0, height1 * 2, width1, height1);
		player_left[1] = sheet.crop(width1, height1 * 2, width1, height1);
		player_left[2] = sheet.crop(width1 * 2, height1 * 2, width1, height1);
		player_left[3] = sheet.crop(width1 * 3, height1 * 2, width1, height1);
		
		player_right = new BufferedImage[4];
		player_right[0] = sheet.crop(0, height1 * 3, width1, height1);
		player_right[1] = sheet.crop(width1, height1 * 3, width1, height1);
		player_right[2] = sheet.crop(width1 * 2, height1 * 3, width1, height1);
		player_right[3] = sheet.crop(width1 * 3, height1 * 3, width1, height1);
		
		player = sheet.crop(0, 0, width1, height1);
		
		brick = tiles.crop(0, 0, width2, height2);
		bridge = tiles.crop(width2, 0, width2, height2);
		flower = tiles.crop(width2 * 2, 0, width2, height2);
		grass = tiles.crop(width2 * 3, 0, width2, height2);
		lightWater = tiles.crop(0, height2, width2, height2);
		path = tiles.crop(width2, height2, width2, height2);
		sand = tiles.crop(width2 * 2, height2, width2, height2);
		water = tiles.crop(width2 * 3, height2, width2, height2);
		
	}
	
}
