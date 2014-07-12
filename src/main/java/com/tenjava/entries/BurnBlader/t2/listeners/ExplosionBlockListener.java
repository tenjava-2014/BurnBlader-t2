package com.tenjava.entries.BurnBlader.t2.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.tenjava.entries.BurnBlader.t2.TenJava;
import com.tenjava.entries.BurnBlader.t2.energy.Energy;
import com.tenjava.entries.BurnBlader.t2.utils.Chat;
import com.tenjava.entries.BurnBlader.t2.utils.Log;

public class ExplosionBlockListener implements Listener {
	
	@EventHandler
	public void onPlace(EntityExplodeEvent event) {
		try {
			for(Block b : event.blockList()) {
				if(b.getType() == Material.REDSTONE_LAMP_OFF) {
					event.blockList().remove(b);
					b.setType(Material.GLOWSTONE);
					b.setMetadata("energy", new FixedMetadataValue(TenJava.get(), "yes"));
				}
			}
		} catch(Exception e) {
			Log.error(e);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() != null) {
			if(event.getClickedBlock().getType() == Material.GLOWSTONE) {
				Block b = event.getClickedBlock();
				if(b.hasMetadata("energy")) {
					Energy.add(event.getPlayer(), 10);
					b.setType(Material.REDSTONE_LAMP_OFF);
					b.removeMetadata("energy", TenJava.get());
					Chat.sendMessage(event.getPlayer(), ChatColor.RED + "You've received 10 energy from this Explosion Block!");
				}
			}
		}
	}
	
}
