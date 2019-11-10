package com.jaguarplugins.youtube.display;

import java.awt.Canvas;
import java.awt.Dimension;


//import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	
	private String TITLE;
	private int WIDTH;
	private int HEIGHT;
	
	public Display(String title, int width, int height) {
		
		TITLE = title;
		WIDTH = width;
		HEIGHT = height;
		
		createDisplay();
	
	}
	
	private void createDisplay() {
		
//		Add an icon in top left
//		ImageIcon logo = new ImageIcon(*path*)
		
		frame = new JFrame(TITLE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
//		frame.setIconImage(null);
		
		canvas = new  Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		
	}
	
	public Canvas getCanvas() {
		
		return canvas;
	
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}
