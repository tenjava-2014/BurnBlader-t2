package com.tenjava.entries.BurnBlader.t2.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.BurnBlader.t2.energy.Energy;

public class EntityListener implements Listener {
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		event.setDroppedExp(0);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setExpToDrop(0);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if(player.getItemInHand().getType() == Material.WOOD_HOE) {
				ItemStack item = player.getItemInHand();
				if(!item.getItemMeta().getDisplayName().equalsIgnoreCase("")) {
					if(ChatColor.stripColor(item.getItemMeta().getDisplayName()).equalsIgnoreCase("Energy Sapper")) {
						if(event.getDamage() > 0.0) {
							Energy.add(player, 1);
						}
					}
				}
			}
		}
	}
	
}
