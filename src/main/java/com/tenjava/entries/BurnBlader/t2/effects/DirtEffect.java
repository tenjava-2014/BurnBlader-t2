package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class DirtEffect extends Effect {

	public DirtEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(Player p) {
		p.teleport(new Location(p.getLocation().getWorld(), p.getLocation().getX(), 16, p.getLocation().getZ()));
		p.getLocation().getBlock().setType(Material.AIR);
		p.getLocation().getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
	}

}
