package com.jaguarplugins.youtube.battle.modes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.jaguarplugins.youtube.Handler;
import com.jaguarplugins.youtube.battle.bosses.Boss;
import com.jaguarplugins.youtube.battle.entities.Weapon;
import com.jaguarplugins.youtube.gfx.Assets;
import com.jaguarplugins.youtube.states.BattleState;

public class AttackMode extends Mode {

	private Weapon hand, stick, potionPoison, sword, spork, potionHealth, firstAidKit;
	private int w;
	private ArrayList<Weapon> weapons;
	private static int lastingDamage;
	
	public AttackMode(Handler handler, Rectangle zone, Boss boss) {
		super(handler, zone, boss);
		weapons = new ArrayList<Weapon>();
		w = 0;
		lastingDamage = 0;
		
//		Weapons
		hand = new Weapon(handler, "Hand", "The power is in your hands", Assets.stick, 1, 1, 1, 0, true);
		weapons.add(hand);
		
		stick = new Weapon(handler, "Stick", "What's brown and sticky?", Assets.stick, 1, 10, 1);
		weapons.add(stick);
		
		potionPoison = new Weapon(handler, "Poison Potion", "Deals low dammage every turn", Assets.stick, 2, 1, 20, 5, false);
		weapons.add(potionPoison);
		
		sword = new Weapon(handler, "Sword", "High Damage", Assets.stick, 5, 1, 20, 0, true);
		weapons.add(sword);
		
		spork = new Weapon(handler, "Spork of Destiny", "Eat salad, soup and ENEMIES!", Assets.stick, 3, 2, 3);
		weapons.add(spork);
		
		potionHealth = new Weapon(true, handler, "Health Potion", "Drink Me.", Assets.stick, 8, 2, 7);
		weapons.add(potionHealth);
		
		firstAidKit = new Weapon(true, handler, "First Aid Kit", "A good weapon", Assets.stick, 3, 7, 3);
		weapons.add(firstAidKit);
	}

	@Override
	public void render(Graphics g) {
		weapons.get(w).render(g);
	}

	@Override
	public void tick() {
		select();
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
			attack();
		}
	}
	
	private void select() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {
			if(w > 0) {
				w -= 1;
			}
		} if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) {
			if(w < weapons.size() - 1) {
				w += 1;
			}
		}
	}
	
	private void attack() {
		boss.hurt(weapons.get(w).getDamage() + lastingDamage);
		BattleState.getPlayerDot().heal(weapons.get(w).getHealing());
		if(BattleState.getPlayerDot().getHealth() + weapons.get(w).getHealing() >= BattleState.getPlayerDot().getTotalhealth()) {
			BattleState.getPlayerDot().resetHealth();
		}
		lastingDamage += weapons.get(w).getLastingDamage();
		if(!weapons.get(w).isInfinite()) {
			weapons.get(w).setQuantity(weapons.get(w).getQuantity() - 1);
			if(weapons.get(w).getQuantity() <= 0) {
				weapons.remove(w);
			}
		}
		w = 0;
		Mode.nextMode();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	public static int getLastingDamage() {
		return lastingDamage;
	}

	@Override
	public void newBoss() {
		lastingDamage = 0;
		
	}

}
