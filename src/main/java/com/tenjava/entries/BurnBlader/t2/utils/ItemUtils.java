package com.tenjava.entries.BurnBlader.t2.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
	
	public static ItemStack rename(ItemStack i, String name) {
		ItemStack item = i;
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(im);
		return item;
	}
	
}
