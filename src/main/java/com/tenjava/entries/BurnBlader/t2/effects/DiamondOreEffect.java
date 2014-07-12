package com.tenjava.entries.BurnBlader.t2.effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DiamondOreEffect extends Effect {

	public DiamondOreEffect(int cost) {
		super(cost);
	}

	@Override
	protected void effect(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 30, 5));
	}

}
