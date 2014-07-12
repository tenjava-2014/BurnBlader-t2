package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.tenjava.entries.BurnBlader.t2.TenJava;
import com.tenjava.entries.BurnBlader.t2.listeners.BlockListener;

public class StoneEffect extends Effect {

	public StoneEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(final Player p) {
		p.setVelocity(new Vector(0, 4, 0));
		BlockListener.fallStomp.add(p.getName());
		Bukkit.getScheduler().runTaskLater(TenJava.get(), new Runnable() {

			@Override
			public void run() {
				p.setVelocity(new Vector(0, -5, 0));
			}
			
		}, 20L);
		Bukkit.getScheduler().runTaskLater(TenJava.get(), new Runnable() {

			@Override
			public void run() {
				BlockListener.fallStomp.remove(p.getName());
			}
			
		}, 100L);
	}

}
