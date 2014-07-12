package com.tenjava.entries.BurnBlader.t2.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import com.tenjava.entries.BurnBlader.t2.TenJava;
import com.tenjava.entries.BurnBlader.t2.effects.AirEffect;
import com.tenjava.entries.BurnBlader.t2.effects.DiamondOreEffect;
import com.tenjava.entries.BurnBlader.t2.effects.DirtEffect;
import com.tenjava.entries.BurnBlader.t2.effects.GlassEffect;
import com.tenjava.entries.BurnBlader.t2.effects.GrassEffect;
import com.tenjava.entries.BurnBlader.t2.effects.NetherrackEffect;
import com.tenjava.entries.BurnBlader.t2.effects.StoneEffect;
import com.tenjava.entries.BurnBlader.t2.effects.WaterEffect;
import com.tenjava.entries.BurnBlader.t2.effects.WoolEffect;
import com.tenjava.entries.BurnBlader.t2.utils.Chat;

public class BlockListener implements Listener {
	
	private HashMap<String, Integer> sneakingTasks = new HashMap<String, Integer>();
	
	public static ArrayList<String> fallStomp = new ArrayList<String>();
	
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
								percentage = 0;
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
				for(Entity e : player.getNearbyEntities(5, 2, 5)) {
					if(e instanceof LivingEntity) {
						((LivingEntity) e).damage(10.0);
						e.setVelocity(new Vector(0, 2, 0));
					}
				}
			}
		}
	}
	
	public void block(final Player player, Material b) {
		if(b == Material.DIAMOND_ORE) {
			new DiamondOreEffect(20).doEffect(player);
		} else if(b == Material.GRASS) {
			new GrassEffect(5).doEffect(player);
		} else if(b == Material.DIRT) {
			new DirtEffect(40).doEffect(player);
		} else if(b == Material.WOOL) {
			new WoolEffect(10).doEffect(player);
		} else if(b == Material.STONE) {
			new StoneEffect(50).doEffect(player);
		} else if(b == Material.GLASS) {
			new GlassEffect(10).doEffect(player);
		} else if(b == Material.AIR) {
			new AirEffect(20).doEffect(player);
		} else if(b == Material.NETHERRACK) {
			new NetherrackEffect(20).doEffect(player);
		} else if(b == Material.WATER) {
			new WaterEffect(20).doEffect(player);
		} else {
			Chat.sendMessage(player, ChatColor.RED + "This block does nothing...");
		}
	}
	
}
