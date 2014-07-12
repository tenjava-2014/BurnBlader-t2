package com.tenjava.entries.BurnBlader.t2.listeners;

import java.util.ArrayList;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.tenjava.entries.BurnBlader.t2.TenJava;
import com.tenjava.entries.BurnBlader.t2.energy.Energy;
import com.tenjava.entries.BurnBlader.t2.utils.Chat;

public class BlockListener implements Listener {
	
	private HashMap<String, Integer> sneakingTasks = new HashMap<String, Integer>();
	
	private ArrayList<String> fallStomp = new ArrayList<String>();
	
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
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(fallStomp.contains(player.getName())) {
				event.setDamage(0.0);
				Block b = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
				b.getRelative(BlockFace.DOWN).setType(b.getType());
				b.setType(Material.AIR);
				Block b1 = b.getRelative(BlockFace.EAST);
				b1.getRelative(BlockFace.DOWN).setType(b1.getType());
				b1.setType(Material.AIR);
				Block b2 = b.getRelative(BlockFace.WEST);
				b2.getRelative(BlockFace.DOWN).setType(b2.getType());
				b2.setType(Material.AIR);
				Block b3 = b.getRelative(BlockFace.NORTH);
				b3.getRelative(BlockFace.DOWN).setType(b3.getType());
				b3.setType(Material.AIR);
				Block b4 = b.getRelative(BlockFace.SOUTH);
				b4.getRelative(BlockFace.DOWN).setType(b4.getType());
				b4.setType(Material.AIR);
			}
		}
	}
	
	public void block(final Player player, Material b) {
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
			if(Energy.get(player) >= 10) {
				player.teleport(player.getBedSpawnLocation());
				Energy.remove(player, 10);
			} else {
				Chat.sendMessage(player, Chat.NOT_ENOUGH_ENERGY.replace("$", "10"));
			}
		} else if(b == Material.STONE) {
			if(Energy.get(player) >= 50) {
				player.setVelocity(new Vector(0, 4, 0));
				fallStomp.add(player.getName());
				Bukkit.getScheduler().runTaskLater(TenJava.get(), new Runnable() {

					@Override
					public void run() {
						player.setVelocity(new Vector(0, -5, 0));
					}
					
				}, 20L);
				Bukkit.getScheduler().runTaskLater(TenJava.get(), new Runnable() {

					@Override
					public void run() {
						fallStomp.remove(player.getName());
					}
					
				}, 100L);
				Energy.remove(player, 50);
			} else {
				Chat.sendMessage(player, Chat.NOT_ENOUGH_ENERGY.replace("$", "10"));
			}
		} else {
			Chat.sendMessage(player, ChatColor.RED + "This block does nothing...");
		}
	}
	
}
