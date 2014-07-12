package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.tenjava.entries.BurnBlader.t2.TenJava;

public class NetherrackEffect extends Effect {

	public NetherrackEffect(int cost) {
		super(cost);
	}

	int id = 0;
	int i = 5;
	@Override
	protected void effect(final Player p) {
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(TenJava.get(), new Runnable() {

			@Override
			public void run() {
				i--;
				if(i == 0) {
					Bukkit.getScheduler().cancelTask(id);
				}
				
				p.getLocation().getBlock().getRelative(BlockFace.SOUTH).setType(Material.FIRE);
			}
			
		}, 0L, 20L);
	}

}
