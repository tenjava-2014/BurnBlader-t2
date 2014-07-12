package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.entity.Player;

import com.tenjava.entries.BurnBlader.t2.energy.Energy;
import com.tenjava.entries.BurnBlader.t2.utils.Chat;

public abstract class Effect {
	
	private int cost;
	
	public Effect(int cost) {
		this.cost = cost;
	}
	
	protected abstract void effect(Player p);
	
	public void doEffect(Player p) {
		if(Energy.get(p) >= 10) {
			effect(p);
			Energy.remove(p, 10);
		} else {
			Chat.sendMessage(p, Chat.NOT_ENOUGH_ENERGY.replace("$", cost + ""));
		}
	}
	
	public int getCost() {
		return cost;
	}
	
}
