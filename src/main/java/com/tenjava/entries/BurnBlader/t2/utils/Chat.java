package com.tenjava.entries.BurnBlader.t2.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {
	
	public static final String NOT_ENOUGH_ENERGY = ChatColor.RED + "You don't have enough energy to do this! You must have $ energy to do this!";
	
	public static void sendMessage(Player player, String s) {
		player.sendMessage(s);
	}
	
}
