package com.tenjava.entries.BurnBlader.t2.energy;

import org.bukkit.entity.Player;

public class Energy {
	
	public static void remove(Player p, int i) {
		p.setLevel(p.getLevel() - i);
	}
	
	public static void add(Player p, int i) {
		p.setLevel(p.getLevel() + i);
	}
	
	public static int get(Player p) {
		return p.getLevel();
	}
	
}
