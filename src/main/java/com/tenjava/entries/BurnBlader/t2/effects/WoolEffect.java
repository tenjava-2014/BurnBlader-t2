package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.entity.Player;

public class WoolEffect extends Effect {

	public WoolEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(Player p) {
		p.teleport(p.getBedSpawnLocation());
	}

}
