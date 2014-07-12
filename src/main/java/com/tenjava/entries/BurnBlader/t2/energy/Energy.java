package com.tenjava.entries.BurnBlader.t2.energy;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.tenjava.entries.BurnBlader.t2.TenJava;
import com.tenjava.entries.BurnBlader.t2.utils.Log;

public class Energy {
	
	private static YamlConfiguration energyConfig;
	private static File energyFile;
	
	public static void remove(Player p, int i) {
		p.setLevel(p.getLevel() - i);
	}
	
	public static void add(Player p, int i) {
		p.setLevel(p.getLevel() + i);
	}
	
	public static int get(Player p) {
		return p.getLevel();
	}
	
	public static void set(File f) {
		energyFile = f;
		energyConfig = YamlConfiguration.loadConfiguration(f);
	}
	
	public static YamlConfiguration getConfig() {
		return energyConfig;
	}
	
	public static File getFile() {
		return energyFile;
	}
	
	public static void save() {
		try {
			for(Player player : Bukkit.getOnlinePlayers()) {
				energyConfig.set("Energy." + player.getUniqueId().toString(), player.getLevel());
			}
			energyConfig.save(energyFile);
		} catch (IOException e) {
			Log.error(e);
		}
	}
	
	public static void loadExpBar() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(energyConfig.contains("Energy." + p.getUniqueId().toString())) {
				p.setLevel(energyConfig.getInt("Energy." + p.getUniqueId().toString()));
			} else {
				p.setLevel(0);
			}
		}
	}
	
	public static void load() {
		File f = new File(TenJava.get().getDataFolder() + "/energy.yml");
		if(!f.exists()) {
			try {
				if(!TenJava.get().getDataFolder().exists()) {
					TenJava.get().getDataFolder().mkdir();
				}
				f.createNewFile();
			} catch (IOException e) {
				Log.error(e);
			}
		}
		Energy.set(f);
	}
	
}
