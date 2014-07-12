package com.tenjava.entries.BurnBlader.t2;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.BurnBlader.t2.listeners.EntityListener;
import com.tenjava.entries.BurnBlader.t2.listeners.WaterSpiralListener;
import com.tenjava.entries.BurnBlader.t2.utils.Log;

public class TenJava extends JavaPlugin {
	
	private static TenJava main;
	
	private YamlConfiguration energyFile;
	
	public void onEnable() {
		main = this;
		Log.info("BurnBlader TenJava Entry", "Starting up...");
		registerListeners(getServer().getPluginManager());
		registerCommands();
		setupEnergyFile();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(energyFile.contains("Energy." + p.getUniqueId().toString())) {
				p.setLevel(energyFile.getInt("Energy." + p.getUniqueId().toString()));
			} else {
				p.setLevel(0);
			}
		}
	}
	
	public void onDisable() {
		Log.info("BurnBlader TenJava Entry", "Closing down...");
	}
	
	void registerListeners(PluginManager pm) {
		pm.registerEvents(new WaterSpiralListener(), this);
		pm.registerEvents(new EntityListener(), this);
	}
	
	void registerCommands() {
		
	}
	
	public static TenJava get() {
		return main;
	}
	
	public YamlConfiguration getEnergyFile() {
		return energyFile;
	}
	
	void setupEnergyFile() {
		File f = new File("plugins/BurnBladerTenJava/energy.yml");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				Log.error(e);
			}
		}
		energyFile = YamlConfiguration.loadConfiguration(f);
	}
	
}
