package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class GrassEffect extends Effect {

	public GrassEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(Player p) {
		for(BlockFace bl : BlockFace.values()) {
			p.getLocation().getBlock().getRelative(bl).setType(Material.AIR);
		}
		p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
	}

}
