package com.jaguarplugins.youtube.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.battle.bosses.Boss;
import com.jaguarplugins.youtube.battle.entities.PlayerDot;
import com.jaguarplugins.youtube.battle.modes.AttackMode;
import com.jaguarplugins.youtube.battle.modes.DefenceMode;
import com.jaguarplugins.youtube.battle.modes.Mode;
import com.jaguarplugins.youtube.battle.modes.StoryMode;
import com.jaguarplugins.youtube.gfx.Animation;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.gfx.Text;

public class BattleState extends State{

//	Generation Settings
	
	private static Rectangle zone;
	
	private static PlayerDot playerDot;
	
	private Mode storyMode, defenceMode, attackMode;
	private static Mode[] modes = new Mode[3];
	private static int currentMode;

	private Animation playerAnim;
	
	private static Boss boss;

		
	public BattleState(Handler handler) {
		super(handler);
		
		playerDot = new PlayerDot(handler, 20, handler.getWidth() / 2 - 15, handler.getHeight() - (handler.getHeight() / 4 - 15));
		
		zone = new Rectangle(10, handler.getHeight() / 2 + 10, handler.getWidth() - 20, handler.getHeight() / 2 - 20);
		
//		TODO sort out so boss != null
		storyMode = new StoryMode(handler, zone, boss);
		modes[0] = storyMode;
		defenceMode = new DefenceMode(handler, zone, boss);
		modes[1] = defenceMode;
		attackMode = new AttackMode(handler, zone, boss);
		modes[2] = attackMode;
		
		currentMode = 0;
		
		playerAnim = new Animation(800, Assets.player_down);
	}

	@Override
	public  void tick() {

		boss.tick();
		modes[currentMode].tick();
//		Player
		playerAnim.tick();

	}

	@Override
	public void render(Graphics g) {
		int xPos = (handler.getWidth() / 4) - (playerAnim.getCurrentFrame().getWidth() * 4 / 2);
		
		boss.render(g);
		modes[currentMode].render(g);
		g.drawRect((int)zone.getX(), (int)zone.getY(), (int)zone.getWidth(), (int)zone.getHeight());
		
//		Player
		g.drawImage(playerAnim.getCurrentFrame(), xPos,30, 200, 276, null);
		if(playerDot.getHealth() > 0) {
		g.setColor(Color.black);
		g.drawRect(xPos - 1, 11, 201, 16);
		g.setColor(Color.green);
		g.fillRect(xPos, 12,
				200 * playerDot.getHealth() / playerDot.getTotalhealth(),
				15);
		}
//		displays an icon when the enemy has lasting damage
		int iconWidth = 20, iconHeight = 20;
		int iconX = boss.getX() + boss.getWidth() - iconWidth;
		int iconY = boss.getY() + boss.getHeight() - iconHeight;
		if(AttackMode.getLastingDamage() > 0) {
			g.setColor(Color.red);
			g.fillRect(iconX, iconY, iconWidth, iconHeight);
			Text.drawString(g, "" + AttackMode.getLastingDamage(), 1 + iconX + iconWidth / 2, 2 + iconY + iconHeight / 2,
					true, Color.black, Assets.font24);
		}
	}
	
	@Override
	public void init() {
		playerDot.resetHealth();
		modes[0].setBoss(boss);
		modes[1].setBoss(boss);
		modes[2].setBoss(boss);
		
		modes[currentMode].init();
		
		modes[0].newBoss();
		modes[1].newBoss();
		modes[2].newBoss();
		
		playerDot.newBoss();
	}
	
	public static Rectangle getZone() {
		return zone;
	}

	public static Mode[] getModes() {
		return modes;
	}

	public static int getCurrentMode() {
		return currentMode;
	}

	public static void setCurrentMode(int index) {
		currentMode = index;
	}

	public static PlayerDot getPlayerDot() {
		return playerDot;
	}

	public static Boss getBoss() {
		return boss;
	}

	public static void setBoss(Boss boss) {
		BattleState.boss = boss;
	}

}
