package com.jaguarplugins.youtube.entities.statics;

import java.awt.Graphics;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.battle.bosses.Boss;
import com.jaguarplugins.youtube.gfx.Animation;
import com.jaguarplugins.youtube.states.BattleState;

public class Enemy extends StaticEntity {

	private Boss boss;
	private Animation anim;
	
	public Enemy(Handler handler, float x, float y, Boss boss) {
		super(handler, x, y, 100, 138);
		this.boss = boss;
		
		anim = new Animation(800, boss.getSprites());
	}

	@Override
	public void tick() {
		anim.tick();
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(anim.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	@Override
	public void die() {
		BattleState.setBoss(boss);
		handler.getGame().getBattleState().initialise();
	}
	
	public Boss getBoss() {
		return boss;
	}

}
