package com.tenjava.entries.BurnBlader.t2;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.BurnBlader.t2.utils.Log;

public class TenJava extends JavaPlugin {
	
	public void onEnable() {
		Log.info("BurnBlader TenJava Entry", "Starting up...");
		registerListeners(getServer().getPluginManager());
		registerCommands();
	}
	
	public void onDisable() {
		Log.info("BurnBlader TenJava Entry", "Closing down...");
	}
	
	void registerListeners(PluginManager pm) {
		
	}
	
	void registerCommands() {
		
	}
	
}
