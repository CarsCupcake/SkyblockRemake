package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

public class WhatThaFuck<T extends Event> implements AbilityManager<T> {

	@Override
	public boolean executeAbility(T event) {
		return false;
	}


}
