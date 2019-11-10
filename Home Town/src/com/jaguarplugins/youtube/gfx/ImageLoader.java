package com.jaguarplugins.youtube.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
		
	}

}

//  To draw an image:
//	g.drawImage(ImageLoader.loadImage("/textures/*file name*"), 0, 0, null);