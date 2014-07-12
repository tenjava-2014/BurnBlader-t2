package com.tenjava.entries.BurnBlader.t2;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.BurnBlader.t2.energy.Energy;
import com.tenjava.entries.BurnBlader.t2.listeners.BlockListener;
import com.tenjava.entries.BurnBlader.t2.listeners.EntityListener;
import com.tenjava.entries.BurnBlader.t2.listeners.ExplosionBlockListener;
import com.tenjava.entries.BurnBlader.t2.listeners.PlayerListener;
import com.tenjava.entries.BurnBlader.t2.listeners.WaterSpiralListener;
import com.tenjava.entries.BurnBlader.t2.utils.Log;
import com.tenjava.entries.BurnBlader.t2.utils.Recepies;

public class TenJava extends JavaPlugin {
	
	private static TenJava main;
	
	public void onEnable() {
		main = this;
		Log.info("BurnBlader TenJava Entry", "Starting up...");
		setup();
	}
	
	public void onDisable() {
		Log.info("BurnBlader TenJava Entry", "Closing down...");
		Energy.save();
	}
	
	void registerListeners(PluginManager pm) {
		pm.registerEvents(new WaterSpiralListener(), this);
		pm.registerEvents(new EntityListener(), this);
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new ExplosionBlockListener(), this);
	}
	
	void registerCommands() {
		
	}
	
	public static TenJava get() {
		return main;
	}
	
	void setup() {
		registerListeners(getServer().getPluginManager());
		registerCommands();
		Energy.load();
		Recepies.load();
		Energy.loadExpBar();
	}
	
}
