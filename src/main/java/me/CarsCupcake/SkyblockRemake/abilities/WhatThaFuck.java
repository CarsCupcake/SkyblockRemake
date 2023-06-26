package me.CarsCupcake.SkyblockRemake.abilities;

import org.bukkit.event.Event;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

public class WhatThaFuck<T extends Event> implements AbilityManager<T> {

	@Override
	public boolean triggerAbility(T event) {
		return false;
	}


}
