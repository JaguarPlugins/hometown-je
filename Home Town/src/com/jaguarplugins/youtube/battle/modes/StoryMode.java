package com.jaguarplugins.youtube.battle.modes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.battle.bosses.Boss;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.gfx.Text;
import com.jaguarplugins.youtube.states.BattleState;

public class StoryMode extends Mode {
	
	private String[][] dialogue;
	private int i1 = 0, i2 = 0;
	
	public StoryMode(Handler handler, Rectangle zone, Boss boss) {
		super(handler, zone, boss);
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
			if(i2 < dialogue[i1].length - 1) {
				i2++;
			} else if(i1 < dialogue.length - 1) {
				i2 = 0;
				i1++;
				Mode.nextMode();
			} else {
				Mode.nextMode();
			}
//			TODO else{pick random sentence}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		Text.drawString(g, "\" " + dialogue[i1][i2] + " \"", (int) BattleState.getZone().getX() + (int) BattleState.getZone().getWidth() / 2,
				(int) BattleState.getZone().getY() + (int) BattleState.getZone().getHeight() / 2, 
				true, Color.black, Assets.font28);
	}

	@Override
	public void init() {
		dialogue = boss.getChat();
	}

	@Override
	public void newBoss() {
		i1 = 0;
		i2 = 0;
	}

}
