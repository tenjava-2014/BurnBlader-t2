package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.tenjava.entries.BurnBlader.t2.TenJava;

public class AirEffect extends Effect {

	public AirEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(final Player p) {
		p.setAllowFlight(true);
		p.setFlying(true);
		Bukkit.getScheduler().runTaskLater(TenJava.get(), new Runnable() {

			@Override
			public void run() {
				p.setAllowFlight(false);
				p.setFlying(false);
			}
			
		}, 60L);
	}

}
