package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.tenjava.entries.BurnBlader.t2.TenJava;

public class GlassEffect extends Effect {

	public GlassEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(final Player p) {
		p.setVelocity(new Vector(0, 4, 0));
		Bukkit.getScheduler().runTaskLater(TenJava.get(), new Runnable() {

			@Override
			public void run() {
				p.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.GLASS);
				p.setVelocity(new Vector(0, 0, 0));
				p.setFallDistance(0F);
			}
			
		}, 20L);
	}

}
