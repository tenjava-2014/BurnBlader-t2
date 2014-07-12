package com.tenjava.entries.BurnBlader.t2.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.tenjava.entries.BurnBlader.t2.TenJava;
import com.tenjava.entries.BurnBlader.t2.energy.Energy;
import com.tenjava.entries.BurnBlader.t2.utils.Chat;

public class BlockListener implements Listener {
	
	private HashMap<String, Integer> sneakingTasks = new HashMap<String, Integer>();
	
	@EventHandler
	public void onSneak(final PlayerToggleSneakEvent event) {
		if(event.isSneaking()) {
			final Block b = event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
			sneakingTasks.put(event.getPlayer().getName(), Bukkit.getScheduler().scheduleSyncRepeatingTask(TenJava.get(), new Runnable() {
				
				private int percentage = 0;
				
				@Override
				public void run() {
					if(sneakingTasks.containsKey(event.getPlayer().getName())) {
						if(event.getPlayer().isSneaking() && event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == b.getType()) {
							if(percentage < 100) {
								percentage = percentage + 5;
								if(percentage % 20 == 0) {
									event.getPlayer().sendMessage(ChatColor.RED + "Energy from " + b.getType().toString().toLowerCase() + " harnessed at " + percentage + "%");
								}
							} else {
								block(event.getPlayer(), b.getType());
								Bukkit.getScheduler().cancelTask(sneakingTasks.get(event.getPlayer().getName()));
								sneakingTasks.remove(event.getPlayer().getName());
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
	
	public void block(Player player, Material b) {
		if(b == Material.DIAMOND_ORE) {
			if(Energy.get(player) >= 30) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 30, 5));
				Energy.remove(player, 30);
			} else {
				Chat.sendMessage(player, Chat.NOT_ENOUGH_ENERGY.replace("$", "30"));
			}
		} else if(b == Material.GRASS) {
			if(Energy.get(player) >= 5) {
				for(BlockFace bl : BlockFace.values()) {
					player.getLocation().getBlock().getRelative(bl).setType(Material.AIR);
				}
				player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
				Energy.remove(player, 5);
			} else {
				Chat.sendMessage(player, Chat.NOT_ENOUGH_ENERGY.replace("$", "5"));
			}
		} else if(b == Material.DIRT) {
			if(Energy.get(player) >= 40) {
				player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), 16, player.getLocation().getZ()));
				player.getLocation().getBlock().setType(Material.AIR);
				player.getLocation().getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
				Energy.remove(player, 40);
			} else {
				Chat.sendMessage(player, Chat.NOT_ENOUGH_ENERGY.replace("$", "40"));
			}
		} else if(b == Material.WOOL) {
			if(Energy.get(player) >= 40) {
				player.teleport(player.getBedSpawnLocation());
				Energy.remove(player, 40);
			} else {
				Chat.sendMessage(player, Chat.NOT_ENOUGH_ENERGY.replace("$", "40"));
			}
		} else {
			Chat.sendMessage(player, ChatColor.RED + "This block does nothing...");
		}
	}
	
}
