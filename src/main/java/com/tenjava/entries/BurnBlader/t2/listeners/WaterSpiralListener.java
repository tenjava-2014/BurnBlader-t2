package com.tenjava.entries.BurnBlader.t2.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import com.tenjava.entries.BurnBlader.t2.TenJava;

public class WaterSpiralListener implements Listener {
		
	private HashMap<String, Integer> sneakingTasks = new HashMap<String, Integer>();
	
	private ArrayList<String> launched = new ArrayList<String>();
	
	private HashMap<String, Integer> yCoord = new HashMap<String, Integer>();
	private HashMap<String, HashMap<Block, Material>> waterBlocks = new HashMap<String, HashMap<Block, Material>>();
	
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
								launch(event.getPlayer());
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
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(launched.contains(event.getPlayer().getName())) {
			if(event.getPlayer().getLocation().getBlockY() < (yCoord.get(event.getPlayer().getName()) + 10)) {
				if(waterBlocks.containsKey(event.getPlayer().getName())) {
					HashMap<Block, Material> blocks = waterBlocks.get(event.getPlayer().getName());
					blocks.put(event.getPlayer().getLocation().getBlock(), event.getPlayer().getLocation().getBlock().getType());
					waterBlocks.remove(event.getPlayer().getName());
					waterBlocks.put(event.getPlayer().getName(), blocks);
				} else {
					HashMap<Block, Material> blocks = new HashMap<Block, Material>();
					blocks.put(event.getPlayer().getLocation().getBlock(), event.getPlayer().getLocation().getBlock().getType());
					waterBlocks.put(event.getPlayer().getName(), blocks);
				}
				event.getPlayer().getLocation().getBlock().setType(Material.STATIONARY_WATER);
			} else {
				launched.remove(event.getPlayer().getName());
				Iterator<Entry<Block, Material>> i = waterBlocks.get(event.getPlayer().getName()).entrySet().iterator();
				while(i.hasNext()) {
			        Map.Entry<Block, Material> blocks = (Map.Entry<Block, Material>) i.next();
			        blocks.getKey().setType(blocks.getValue());
			        i.remove();
			    }
				waterBlocks.remove(event.getPlayer());
				yCoord.remove(event.getPlayer());
			}
		}
	}
	
	public void launch(Player player) {
		for(BlockFace b : BlockFace.values()) {
			player.getLocation().getBlock().getRelative(b).setType(Material.AIR);
		}
		player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
		yCoord.put(player.getName(), player.getLocation().getBlockY());
		player.setVelocity(new Vector(0, 5, 0));
		launched.add(player.getName());
	}
	
}
