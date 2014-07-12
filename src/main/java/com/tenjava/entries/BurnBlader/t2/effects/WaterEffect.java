package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WaterEffect extends Effect {

	public WaterEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(Player p) {
		p.setVelocity(new Vector(0, 3, 0));
	}

}
