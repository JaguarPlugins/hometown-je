package com.jaguarplugins.youtube.battle.modes;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.battle.bosses.Boss;
import com.jaguarplugins.youtube.states.BattleState;

public abstract class Mode {
	
	protected Handler handler;
	protected Rectangle zone;
	protected Boss boss;
	
	public Mode(Handler handler, Rectangle zone, Boss boss) {
		this.handler = handler;
		this.zone = zone;
		this.boss = boss;
	}
	
	public abstract void init();
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public abstract void newBoss();
	
	public static void nextMode() {
		if(BattleState.getCurrentMode() < BattleState.getModes().length - 1) {
			BattleState.setCurrentMode(BattleState.getCurrentMode() + 1);
		} else {
			BattleState.setCurrentMode(0);
		}
		BattleState.getModes()[BattleState.getCurrentMode()].init();
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
	
}
