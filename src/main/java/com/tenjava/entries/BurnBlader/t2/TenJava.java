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
	
	private YamlConfiguration energyConfig;
	private File energyFile;
	
	public void onEnable() {
		main = this;
		Log.info("BurnBlader TenJava Entry", "Starting up...");
		registerListeners(getServer().getPluginManager());
		registerCommands();
		setupEnergyFile();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(energyConfig.contains("Energy." + p.getUniqueId().toString())) {
				p.setLevel(energyConfig.getInt("Energy." + p.getUniqueId().toString()));
			} else {
				p.setLevel(0);
			}
		}
	}
	
	public void onDisable() {
		Log.info("BurnBlader TenJava Entry", "Closing down...");
		for(Player player : Bukkit.getOnlinePlayers()) {
			TenJava.get().getEnergyFile().set("Energy." + player.getUniqueId().toString(), player.getLevel());
		}
		try {
			energyConfig.save(energyFile);
		} catch (IOException e) {
			Log.error(e);
		}
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
		return energyConfig;
	}
	
	void setupEnergyFile() {
		energyFile = new File(getDataFolder() + "/energy.yml");
		if(!energyFile.exists()) {
			try {
				if(!getDataFolder().exists()) {
					getDataFolder().mkdir();
				}
				energyFile.createNewFile();
			} catch (IOException e) {
				Log.error(e);
				Log.info("error", energyFile.getAbsolutePath());
			}
		}
		energyConfig = YamlConfiguration.loadConfiguration(energyFile);
	}
	
}
