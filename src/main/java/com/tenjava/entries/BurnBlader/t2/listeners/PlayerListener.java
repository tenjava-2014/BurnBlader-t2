package com.tenjava.entries.BurnBlader.t2.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.tenjava.entries.BurnBlader.t2.energy.Energy;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if(Energy.getConfig().contains("Energy." + p.getUniqueId().toString())) {
			p.setLevel(Energy.getConfig().getInt("Energy." + p.getUniqueId().toString()));
		} else {
			p.setLevel(0);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Energy.getConfig().set("Energy." + event.getPlayer().getUniqueId().toString(), event.getPlayer().getLevel());
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent event) {
		Energy.getConfig().set("Energy." + event.getPlayer().getUniqueId().toString(), event.getPlayer().getLevel());
	}
	
}
