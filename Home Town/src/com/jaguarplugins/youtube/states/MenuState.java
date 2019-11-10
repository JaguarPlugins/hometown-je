package com.jaguarplugins.youtube.states;

import java.awt.Color;
import java.awt.Graphics;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.ui.ClickListener;
import com.jaguarplugins.youtube.ui.UIImageButton;
import com.jaguarplugins.youtube.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(
				handler.getGame().getWidth() / 2 - Assets.start[0].getWidth() / 2,
				Assets.logo.getHeight(),
				Assets.start[0].getHeight(),
				Assets.start[0].getWidth(),
				Assets.start,
				new ClickListener(){

					@Override
					public void onClick() {
						State.setState(handler.getGame().gameState);
					}
				}
				));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
		uiManager.render(g);
		g.drawImage(Assets.logo, handler.getGame().getWidth() / 2 - Assets.logo.getWidth() / 2, 0, null);
		
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
	
}
