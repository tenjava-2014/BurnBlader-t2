package com.tenjava.entries.BurnBlader.t2.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.tenjava.entries.BurnBlader.t2.TenJava;
import com.tenjava.entries.BurnBlader.t2.effects.WaterEffect;

public class WaterSpiralListener implements Listener {
		
	private HashMap<String, Integer> sneakingTasks = new HashMap<String, Integer>();
	
	@EventHandler
	public void onSneak(final PlayerToggleSneakEvent event) {
		if(event.isSneaking() && event.getPlayer().getLocation().getBlock().getType() == Material.STATIONARY_WATER ||
				event.isSneaking() && event.getPlayer().getLocation().getBlock().getType() == Material.WATER) {
			sneakingTasks.put(event.getPlayer().getName(), Bukkit.getScheduler().scheduleSyncRepeatingTask(TenJava.get(), new Runnable() {
				
				private int percentage = 0;
				
				@Override
				public void run() {
					if(sneakingTasks.containsKey(event.getPlayer().getName())) {
						if(event.getPlayer().isSneaking() && event.getPlayer().getLocation().getBlock().getType() == Material.STATIONARY_WATER ||
								event.getPlayer().isSneaking() && event.getPlayer().getLocation().getBlock().getType() == Material.WATER) {
							if(percentage < 100) {
								percentage = percentage + 5;
								if(percentage % 20 == 0) {
									event.getPlayer().sendMessage(ChatColor.RED + "Energy from water harnessed at " + percentage + "%");
								}
							} else {
								new WaterEffect(10).doEffect(event.getPlayer());
							}
						} else {
							Bukkit.getScheduler().cancelTask(sneakingTasks.get(event.getPlayer().getName()));
							sneakingTasks.remove(event.getPlayer().getName());
						}
					}
				}
				
			}, 0L, 5L));
		}
	}
	
}
