package com.tenjava.entries.BurnBlader.t2.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.tenjava.entries.BurnBlader.t2.TenJava;

public class Recepies {
	
	public static void load() {
		ShapedRecipe g = new ShapedRecipe(ItemUtils.rename(new ItemStack(Material.WOOD_HOE), ChatColor.DARK_PURPLE + "Energy Sapper")).
				shape("$$$", "$%$", "$%$").
				setIngredient('$', Material.DIAMOND).
				setIngredient('%', Material.STICK);
		TenJava.get().getServer().addRecipe(g);
	}
	
}
